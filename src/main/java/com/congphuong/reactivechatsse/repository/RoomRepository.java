package com.congphuong.reactivechatsse.repository;

import com.congphuong.reactivechatsse.entity.Room;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;


public interface RoomRepository extends ReactiveMongoRepository<Room, String> {

    Flux<Room> findRoomsByRoomNameLike(String roomName);

    Flux<Room> findAllByRoomNameMatchesRegex(String roomName);

    Flux<Room> findAllBy(TextCriteria criteria, Pageable pageable);

}
