package com.congphuong.reactivechatsse.repository;

import com.congphuong.reactivechatsse.entity.Room;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;


public interface RoomRepository extends ReactiveMongoRepository<Room, String> {

}
