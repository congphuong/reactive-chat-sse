package com.congphuong.reactivechatsse.bot;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Bot {
    private final WebClient webClient;
    private final String apiKey = "86655b533e7fecade46a2bb05516413c";

    public Bot(){
        this.webClient = WebClient.create();
    }
}
