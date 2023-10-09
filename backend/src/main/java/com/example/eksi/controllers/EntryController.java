//package com.example.eksi.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.eksi.HelloWorldBean;
//import com.example.eksi.domain.Topic;
//import com.example.eksi.repositories.TopicRepository;
//
//@RestController
//@RequestMapping(value = "/api/")
//public class EntryController {
//
//	@Autowired
//	private TopicRepository topicRepository;
//
////	@Autowired
////	private HelloWorldBean helloWorldBean;
//
//	@GetMapping(value = "/entries")
//	public List<Topic> getEntries() {
//		List<Topic> repos = topicRepository.findAll();
//		return repos;
//	}
//
//	@GetMapping(value = "/test")
//	public HelloWorldBean getTest() {
//		return new HelloWorldBean("my-bean-value");
////		return helloWorldBean;
//	}
//
//	@GetMapping(value = "/")
//	public String getRoot() {
//		return "This is root";
//	}
//
//}
