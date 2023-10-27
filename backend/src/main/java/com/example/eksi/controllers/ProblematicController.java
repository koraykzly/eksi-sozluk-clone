package com.example.eksi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eksi.payload.response.ProblematicAnswersDto;
import com.example.eksi.repositories.projections.IProblematicTitle;
import com.example.eksi.repositories.projections.IProblematicTitleWithCount;
import com.example.eksi.services.ProblematicService;

@RestController
@RequestMapping("/api/problematics")
public class ProblematicController {

    @Autowired
    private ProblematicService problematicService;

    @GetMapping("/today")
    public ResponseEntity<List<IProblematicTitle>> getTodayProblematics() {
        List<IProblematicTitle> todayProblematics = problematicService.getTodaysProblematic();
        return ResponseEntity.ok(todayProblematics);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<IProblematicTitleWithCount>> getPopularProblematics() {
        List<IProblematicTitleWithCount> popularProblematics = problematicService.getPopularProblematic();
        return ResponseEntity.ok(popularProblematics);
    }

    @GetMapping("/id/{problematicId}")
    public ResponseEntity<ProblematicAnswersDto> getById(
            @PathVariable Long problematicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ProblematicAnswersDto problematic = problematicService.getProblematic(problematicId, page, size);
        return ResponseEntity.ok(problematic);
    }

}
