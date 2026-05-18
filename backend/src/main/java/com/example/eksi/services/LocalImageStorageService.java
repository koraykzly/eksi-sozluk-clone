package com.example.eksi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class LocalImageStorageService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private static final Map<String, String> EXTENSIONS_BY_CONTENT_TYPE = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png",
            "image/webp", ".webp"
    );

    private final Path uploadRoot;
    private final String publicUrlPrefix;
    private final long maxProfileImageSizeBytes;

    public LocalImageStorageService(
            @Value("${app.upload.root:uploads}") String uploadRoot,
            @Value("${app.upload.public-url-prefix:/uploads}") String publicUrlPrefix,
            @Value("${app.upload.profile-image.max-size-bytes:2097152}") long maxProfileImageSizeBytes) {
        this.uploadRoot = Paths.get(uploadRoot).toAbsolutePath().normalize();
        this.publicUrlPrefix = normalizePublicUrlPrefix(publicUrlPrefix);
        this.maxProfileImageSizeBytes = maxProfileImageSizeBytes;
    }

    public StoredImage storeProfileImage(Long userId, MultipartFile image) {
        validateImage(image);

        String extension = EXTENSIONS_BY_CONTENT_TYPE.get(image.getContentType());
        String filename = UUID.randomUUID() + extension;
        String key = "profile-images/" + userId + "/" + filename;
        Path destination = resolveKey(key);

        try {
            Files.createDirectories(destination.getParent());
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Could not store profile image", ex);
        }

        return new StoredImage(key, publicUrlPrefix + "/" + key);
    }

    public void delete(String key) {
        if (!StringUtils.hasText(key)) {
            return;
        }

        try {
            Files.deleteIfExists(resolveKey(key));
        } catch (IOException ex) {
            throw new IllegalStateException("Could not delete previous profile image", ex);
        }
    }

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Profile image is required");
        }

        if (image.getSize() > maxProfileImageSizeBytes) {
            throw new IllegalArgumentException("Profile image size must be at most "
                    + maxProfileImageSizeBytes + " bytes");
        }

        if (!ALLOWED_CONTENT_TYPES.contains(image.getContentType())) {
            throw new IllegalArgumentException("Only JPEG, PNG, and WEBP images are allowed");
        }
    }

    private Path resolveKey(String key) {
        Path path = uploadRoot.resolve(key).normalize();
        if (!path.startsWith(uploadRoot)) {
            throw new IllegalArgumentException("Invalid image path");
        }
        return path;
    }

    private String normalizePublicUrlPrefix(String value) {
        String normalized = value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
        return normalized.startsWith("/") ? normalized : "/" + normalized;
    }

    public Path getUploadRoot() {
        return uploadRoot;
    }

    public record StoredImage(String key, String url) {
    }
}
