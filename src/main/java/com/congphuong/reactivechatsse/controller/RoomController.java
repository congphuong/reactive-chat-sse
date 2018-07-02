package com.congphuong.reactivechatsse.controller;

import com.congphuong.reactivechatsse.entity.Room;
import com.congphuong.reactivechatsse.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/room")
    public Flux<Room> getRoom() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{roomid}")
    public Mono<Room> getRoomById(@PathVariable(value = "roomid") String roomId) {
        return roomRepository.findById(roomId);
    }

    @PostMapping("/room")
    public Mono<Room> createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/room/search")
    public  Flux<Room> searchRoom(@RequestParam(value = "roomName") String roomName){
        if(roomName == ""){
            return Flux.empty();
        }
        System.out.println(roomName);
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(roomName);
        Pageable pageRequest = new PageRequest(0, 10);
        return roomRepository.findAllBy(criteria, pageRequest);
    }
}
