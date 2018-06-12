package com.congphuong.reactivechatsse.controller;


import com.congphuong.reactivechatsse.entity.ChatMessage;
import com.congphuong.reactivechatsse.entity.User;
import com.congphuong.reactivechatsse.repository.ChatRepository;
import com.congphuong.reactivechatsse.repository.UserRepository;
import com.congphuong.reactivechatsse.request.ChatMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ChatMessage> createChatMessage(@Valid @RequestBody ChatMessageModel chatMessageModel, Principal principal){
        return Mono.justOrEmpty(principal.getName())
                .flatMap(name -> userRepository.findUserByUsername(name))
                .flatMap(user -> {
                    ChatMessage chatMessage = new ChatMessage(chatMessageModel);
                    chatMessage.setUser(user);
                    return chatRepository.save(chatMessage);
                });

    }

    @GetMapping("/userdetails")
    public Mono<String> principal(Mono<Principal> principal) {
        return principal.map( p -> "Hello " + p.toString() );
    }

    @GetMapping("/message/{roomId}/{offSet}")
    public Flux<ChatMessage> getChatMessages(@PathVariable(value = "roomId") String roomId, @PathVariable(value = "offSet") String offSet){
        try {
            Pageable pageable = new PageRequest(Integer.parseInt(offSet), 30);
            return chatRepository.findByRoomIdOrderByCreateAtDesc(roomId, pageable);
        } catch (Exception e){
            e.printStackTrace();
            return Flux.error(new IllegalArgumentException("Invalid parameter"));
        }
    }

    @GetMapping(value = "/stream/chat/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> streamChatMessage(@PathVariable(value = "roomId") String roomId){
        Date date = new Date();
        return chatRepository.findByRoomIdAndCreateAtGreaterThanEqual(roomId,date);
    }
}
