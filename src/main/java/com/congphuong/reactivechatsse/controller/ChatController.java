package com.congphuong.reactivechatsse.controller;


import com.congphuong.reactivechatsse.entity.ChatMessage;
import com.congphuong.reactivechatsse.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin
@RestController
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;

    @PostMapping("/message")
    public Mono<ChatMessage> createChatMessage(@Valid @RequestBody ChatMessage chatMessage){
        return chatRepository.save(chatMessage);
    }

    @GetMapping("/message/{roomId}/{offSet}")
    public Flux<ChatMessage> getChatMessages(@PathVariable(value = "roomId") String roomId, @PathVariable(value = "offSet") String offSet){
        try {
            Pageable pageable = new PageRequest(Integer.parseInt(offSet), 5);
            return chatRepository.findByRoomId(roomId, pageable);
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
