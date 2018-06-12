package com.congphuong.reactivechatsse.repository;

import com.congphuong.reactivechatsse.entity.ChatMessage;
import com.congphuong.reactivechatsse.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ChatRepository extends ReactiveMongoRepository<ChatMessage, String> {

    @Tailable
    public Flux<ChatMessage> findByRoomIdAndCreateAtGreaterThanEqual(String roomId, Date creatAt);

    Flux<ChatMessage> findByRoomIdOrderByCreateAtDesc(String roomId, Pageable pageable);

}
