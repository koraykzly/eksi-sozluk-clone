package com.example.eksi.schedulars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.eksi.repositories.TopicRepository;

@Component
public class EntrySchedular {

    private static final Logger logger = LoggerFactory.getLogger(EntrySchedular.class);

    @Autowired
    TopicRepository topicRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void setZeroEntryCountSinceMidnight() {
        long startTime = System.currentTimeMillis();

        topicRepository.setZeroEntryCountSinceMidnight();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("All topic record's entryCountSinceMidnigth set to 0 in {}ms", executionTime);

    }
}
