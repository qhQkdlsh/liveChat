package com.dadaok86.livechat.service;

import com.dadaok86.livechat.dao.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    /**
     * 채팅방 전체조회
     */
    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    /**
     * 채팅방번호로 조회
     */
    public ChatRoom findRoomById(String roomid){
        return chatRooms.get(roomid);
    }

    /**
     * 채팅방생성 : 고유의 채팅방id를 가진 채팅방객체 생성
     */
    public ChatRoom createRoom(String name){
        String randomid = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomid)
                .name(name)
                .build();
        chatRooms.put(randomid, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }
}
