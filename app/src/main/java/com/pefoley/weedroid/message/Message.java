package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class Message {

    String id;
    MsgType type;

    public static Message parseMessage(String id, MsgType type, ByteBuffer buffer) {
        switch (type) {
            case CHR:
                return new CharMessage(id, buffer);
            default:
                throw new RuntimeException("Unsupported type");
        }
    }

    protected Message(String id, MsgType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }
}
