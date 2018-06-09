package com.congphuong.reactivechatsse.controller;

import com.congphuong.reactivechatsse.entity.Room;
import com.congphuong.reactivechatsse.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/room")
    public Flux<Room> getRoom() {
        return roomRepository.findAll();
    }

    @PostMapping("/room")
    public Mono<Room> createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }
}
