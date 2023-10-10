package com.example.eksi.runners;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.eksi.repositories.UserRepository;
import com.example.eksi.domain.User;
import com.example.eksi.domain.enums.*;

@Component
public class PopulateDatabase implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("johndoe").isEmpty()) {
            User user1 = new User("johndoe", "johndoe@example.com",
                    passwordEncoder.encode("dummy"),
                    LocalDate.now(), EGender.MALE, ERole.USER);
            userRepository.save(user1);

            System.out.println("User generated: " + user1);

            user1 = userRepository.findByUsername(user1.getUsername()).orElse(null);
            System.out.println("User fetched: " + user1);

        } else {
            System.out.println("User already exists: johndoe");
        }

    }
}
