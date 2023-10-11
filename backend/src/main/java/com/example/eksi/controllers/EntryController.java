package com.example.eksi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.request.EntryRequest;
import com.example.eksi.payload.response.EntryDto;
import com.example.eksi.security.services.UserDetailsImpl;
import com.example.eksi.services.EntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/entries/")
public class EntryController {

    private static final Logger logger = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private EntryService entryService;

    @PostMapping(value = "/")
    public EntryDto insertEntry(
            @Valid @RequestBody EntryRequest requestBody) {

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return entryService.addEntry(
                requestBody.getContent(), requestBody.getTopicId(), user.getId());
    }

}
