package com.sakinramazan.cache.redis.controller;

import com.sakinramazan.cache.redis.dao.UserRepository;
import com.sakinramazan.cache.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usersapi")
public class UserController {

    // Sample usage of Cacheable, CacheEvict, CachePut
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@PathVariable User user) {
        return userRepository.save(user);
    }

    // Conditional Caching
    @Cacheable(value = "users", key = "#userId", unless = "#result.numberOfFriends < 15")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        return userRepository.findOne(Long.valueOf(userId));
    }

    // CacheEvit for delete all first then put new ones
    @CacheEvict(value = "users", allEntries = true)
    @DeleteMapping("/{userId}")
    public void deleteUserByID(@PathVariable Long userId) {
        userRepository.delete(userId);
    }

    // CachePut for not deleting , just adding new one
    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
