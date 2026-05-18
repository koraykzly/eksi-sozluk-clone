package com.example.eksi.schedulars;

import com.example.eksi.repositories.TopicRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EntrySchedular {

    private static final Logger logger = LoggerFactory.getLogger(EntrySchedular.class);

    private final TopicRepository topicRepository;

    public EntrySchedular(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetEntryCountSinceMidnight() {
        long start = System.currentTimeMillis();
        int updatedRowCount = topicRepository.resetEntryCountSinceMidnight();
        logger.info("Reset entryCountSinceMidnight for {} topics in {} ms", updatedRowCount,
                System.currentTimeMillis() - start);
    }
}
