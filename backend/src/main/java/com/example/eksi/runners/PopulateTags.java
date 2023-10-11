package com.example.eksi.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.eksi.domain.Tag;
import com.example.eksi.repositories.TagRepository;

import jakarta.transaction.Transactional;

@Component
public class PopulateTags implements CommandLineRunner {

    @Autowired
    TagRepository tagRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String[] tags = {
                "haber", "sinema", "bilim", "eğitim", "spoiler",
                "müzik", "edebiyat", "ekonomi", "tarih", "yeme-içme",
                "ilişkiler", "teknoloji", "siyaset", "sanat",
                "moda", "otomativ", "magazin", "spor", "ekşi-sözlük",
                "motosiklet", "sağlık", "oyun", "programlama", "tv",
                "seyehat", "havacılık"
        };

        for (String tag : tags) {
            if (!tagRepository.existsByName(tag))
                tagRepository.save(new Tag(tag));
        }
    }
}
