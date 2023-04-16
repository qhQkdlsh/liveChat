package com.dadaok86.livechat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class WebSocketChattingHandler extends TextWebSocketHandler {
    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        String payload = message.getPayload();
        log.info("#1.payload {}",payload);

        TextMessage textMessage = new TextMessage("Chatting 서버접속 테스트 완료.");
        session.sendMessage(textMessage);
    }

    @Configuration
    @EnableWebSocket
    public class WebSocketConfig implements WebSocketConfigurer {

        @Autowired
        private WebSocketChattingHandler webSocketChattingHandler;

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(webSocketChattingHandler, "/ws/chat").setAllowedOriginPatterns("*");
        }
    }
}
