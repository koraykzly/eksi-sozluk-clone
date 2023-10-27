package com.example.eksi.payload.response;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.example.eksi.repositories.projections.IProblematic;
import com.example.eksi.repositories.projections.IProblematicAnswer;

public class ProblematicAnswersDto {
    private Long problematicId;
    private String problematicTitle;
    private Long topicId;
    private String topicTitle;
    private int upvoted;
    private int downvoted;
    private LocalDateTime datetime;
    
    private Page<IProblematicAnswer> answers;

    public ProblematicAnswersDto(Long problematicId, String problematicTitle, Long topicId, String topicTitle,
            Page<IProblematicAnswer> answers) {
        super();
        this.problematicId = problematicId;
        this.problematicTitle = problematicTitle;
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.answers = answers;
    }
    
    public ProblematicAnswersDto(IProblematic problematic, Page<IProblematicAnswer> answers) {
        super();
        this.problematicId = problematic.getProblematicId();
        this.problematicTitle = problematic.getProblematicTitle();
        this.topicId = problematic.getTopicId();
        this.topicTitle = problematic.getTopicTitle();
        this.upvoted = problematic.getUpvoted();
        this.downvoted = problematic.getDownvoted();
        this.datetime = problematic.getDatetime();
        this.answers = answers;
    }

    public Long getProblematicId() {
        return problematicId;
    }

    public void setProblematicId(Long problematicId) {
        this.problematicId = problematicId;
    }

    public String getProblematicTitle() {
        return problematicTitle;
    }

    public void setProblematicTitle(String problematicTitle) {
        this.problematicTitle = problematicTitle;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Page<IProblematicAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Page<IProblematicAnswer> answers) {
        this.answers = answers;
    }

}