package com.example.eksi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.request.EntryRequest;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.repositories.projections.IEntry;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.EntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/entries/")
public class EntryController {

    // private static final Logger logger = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private EntryService entryService;

    @GetMapping(value = "/")
    public List<IEntry> getRandomEntries() {
        return entryService.getRandomEntries();
    }

    @PostMapping(value = "/")
    public EntryDto insertEntry(
            @Valid @RequestBody EntryRequest requestBody) {

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return entryService.addEntry(
                requestBody.getContent(), requestBody.getTopicId(), user.getId());
    }

    @GetMapping(value = "/{entryId}")
    public IEntry getEntry(@PathVariable Long entryId) {
        return entryService.getEntry(entryId);
    }

    @PostMapping(value = "/favorities/{entryId}")
    public IEntry addEntryToFavorite(@PathVariable Long entryId) {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        return entryService.addEntryToFavorities(user.getId(), entryId);

    }

    @GetMapping(value = "/entry/{username}")
    public Page<IEntry> getEntriesByUsernameWithPagination(@PathVariable String username) {
        return entryService.getEntriesByUsernameWithPagination(username);

    }
    
    

}
