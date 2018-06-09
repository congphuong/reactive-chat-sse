package com.congphuong.reactivechatsse.repository;

import com.congphuong.reactivechatsse.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<UserDetails> findByUsername(String username);
    Mono<User> findUserByUsername(String username);
}
