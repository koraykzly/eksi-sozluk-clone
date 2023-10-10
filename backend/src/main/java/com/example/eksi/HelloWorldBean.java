package com.example.eksi;

//import org.springframework.context.annotation.Bean;

//import org.springframework.context.annotation.Configuration;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        System.out.println("HellowWorldBean Constructor message: " + message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}

//@Configuration
//class CustomConf {
//	
//	@Bean
//	public HelloWorldBean myFunc() {
//		System.out.println("myFunc inside returns HelloWorldBean");
//		return new HelloWorldBean("test");
//	}
//}
