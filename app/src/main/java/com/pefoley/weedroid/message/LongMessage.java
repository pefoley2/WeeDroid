package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class LongMessage extends Message {

    private long data;

    LongMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.LON);
        this.data = Long.decode(MessageParser.parseString(buffer, (int) buffer.get()));
    }

    public String toString() {
        return String.valueOf(data);
    }
}
