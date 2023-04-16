package com.dadaok86.livechat.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type;
    private String roomid;
    private String sender;
    private String message;
}
