package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public abstract class Message {

    String id;
    MsgType type;

    public static Message parseMessage(String id, MsgType type, ByteBuffer buffer) {
        switch (type) {
            case CHR:
                return new CharMessage(id, buffer);
            case INF:
                return new InfoMessage(id, buffer);
            default:
                throw new RuntimeException("Unsupported message type:" + type);
        }
    }

    protected Message(String id, MsgType type) {
        this.id = id;
        this.type = type;
    }

    public abstract String toString();

    public String getId() {
        return id;
    }
}
