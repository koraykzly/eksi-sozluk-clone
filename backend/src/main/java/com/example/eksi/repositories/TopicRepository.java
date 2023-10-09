package com.example.eksi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eksi.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
