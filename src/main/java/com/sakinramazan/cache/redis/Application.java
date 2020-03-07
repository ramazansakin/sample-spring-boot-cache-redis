package com.sakinramazan.cache.redis;

import com.sakinramazan.cache.redis.dao.UserRepository;
import com.sakinramazan.cache.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {

        // Creating sample test users
        User shubham = new User(1L, "Sample User1", 15);
        User pankaj = new User(2L, "Sample User2", 5);
        User lewis = new User(3L, "Sample User3", 21);

        userRepository.save(shubham);
        userRepository.save(pankaj);
        userRepository.save(lewis);

        // Print all users
        System.out.println(userRepository.findAll());
    }
}
