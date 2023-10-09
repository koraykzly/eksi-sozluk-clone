//package com.example.eksi.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.eksi.HelloWorldBean;
//import com.example.eksi.repositories.TopicRepository;
//import com.example.eksi.services.TopicService;
//
//@RestController
//@RequestMapping(value = "/api/")
//public class TopicController {
//
//	@Autowired
//	private TopicRepository topicRepository;
//	
//	@Autowired
//	private TopicService topicService;
//
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
