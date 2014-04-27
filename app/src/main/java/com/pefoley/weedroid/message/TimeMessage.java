package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class TimeMessage extends Message {

    private long data;

    TimeMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.TIM);
        this.data = Long.decode(MessageParser.parseString(buffer, (int) buffer.get()));
    }

    public String toString() {
        return String.valueOf(data);
    }
}
