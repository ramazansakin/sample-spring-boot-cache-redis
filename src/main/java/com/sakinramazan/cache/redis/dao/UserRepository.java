package com.sakinramazan.cache.redis.dao;

import com.sakinramazan.cache.redis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByNameAndAndNumberOfFriends();
}
