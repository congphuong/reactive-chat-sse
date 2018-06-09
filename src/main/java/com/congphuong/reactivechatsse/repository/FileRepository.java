package com.congphuong.reactivechatsse.repository;

import com.congphuong.reactivechatsse.entity.File;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FileRepository extends ReactiveMongoRepository<File, String> {
}
