package com.congphuong.reactivechatsse.controller;

import com.congphuong.reactivechatsse.entity.User;
import com.congphuong.reactivechatsse.repository.UserRepository;
import com.congphuong.reactivechatsse.request.UpdateUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    public Mono<User> getUserByName(@PathVariable(value = "username") String username) {

        return userRepository.findUserByUsername(username);
    }

    @PutMapping("/user/{username}")
    public Mono<User> updateUserByName(@PathVariable(value = "username") String username, @RequestBody UpdateUserModel updateUserModel) {

        return userRepository.findUserByUsername(username).flatMap(user -> {
            user.setUrl(updateUserModel.getUrl());
            return userRepository.save(user);
        });
    }
}
