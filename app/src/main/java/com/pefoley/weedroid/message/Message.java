package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public abstract class Message {

    protected String id;
    private MsgType type;

    protected Message(String id, MsgType type) {
        this.id = id;
        this.type = type;
    }

    public static Message parseMessage(String id, MsgType type, ByteBuffer buffer) {
        switch (type) {
            case CHR:
                return new CharMessage(id, buffer);
            case INT:
                return new IntMessage(id, buffer);
            case LON:
                return new LongMessage(id, buffer);
            case STR:
                return new StringMessage(id, buffer);
            case BUF:
                return new BufferMessage(id, buffer);
            case PTR:
                return new PointerMessage(id, buffer);
            case TIM:
                return new TimeMessage(id, buffer);
            case HTB:
                return new HashTableMessage(id, buffer);
            case HDA:
                return new HDataMessage(id, buffer);
            case INF:
                return new InfoMessage(id, buffer);
            case INL:
                return new InfoListMessage(id, buffer);
            case ARR:
                return new ArrayMessage(id, buffer);
            default:
                throw new RuntimeException("Unsupported message type:" + type);
        }
    }

    public abstract String toString();

    public MsgType getType() {
        return type;
    }

}
