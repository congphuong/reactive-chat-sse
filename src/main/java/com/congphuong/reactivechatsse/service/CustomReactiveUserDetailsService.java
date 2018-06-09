package com.congphuong.reactivechatsse.service;

import com.congphuong.reactivechatsse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<UserDetails> data = userRepository.findByUsername(username);
        return data;
    }
}