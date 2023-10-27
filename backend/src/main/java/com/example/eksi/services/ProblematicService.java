package com.example.eksi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.eksi.exceptions.NotFoundException;
import com.example.eksi.payload.response.ProblematicAnswersDto;
import com.example.eksi.repositories.ProblematicRepository;
import com.example.eksi.repositories.projections.IProblematic;
import com.example.eksi.repositories.projections.IProblematicAnswer;
import com.example.eksi.repositories.projections.IProblematicTitle;
import com.example.eksi.repositories.projections.IProblematicTitleWithCount;

@Service
public class ProblematicService {

    @Autowired
    private ProblematicRepository problematicRepository;

    public List<IProblematicTitle> getTodaysProblematic() {
        return problematicRepository.findTodayProblematics();
    }

    public List<IProblematicTitleWithCount> getPopularProblematic() {
        return problematicRepository.findPopularProblematics();
    }

    public ProblematicAnswersDto getProblematic(Long problematicId, int page, int size) {
        IProblematic problematic = problematicRepository.findByIdWithTopic(problematicId).orElseThrow(
                () -> new NotFoundException("Problematic not found"));

        Sort sort = Sort.by(Sort.Direction.ASC, "datetime");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<IProblematicAnswer> answers = problematicRepository.findAllProblematicAnswers(
                problematicId, pageRequest);

        return new ProblematicAnswersDto(problematic, answers);

    }

}
