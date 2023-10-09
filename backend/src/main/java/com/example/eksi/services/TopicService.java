package com.example.eksi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eksi.domain.Topic;
import com.example.eksi.repositories.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	TopicRepository topicRepository;

	// bugün
	public List<Topic> getTodayTopics() {
		return null;
	}
	
	// gündem
	public List<Topic> getPopularTopics() {
		return null;
	}
	
	public List<Topic> searchTopics() {
		return null;
	}
}
