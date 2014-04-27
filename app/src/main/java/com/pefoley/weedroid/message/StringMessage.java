package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class StringMessage extends Message {

    private String data;

    StringMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.STR);
        this.data = MessageParser.parseString(buffer, buffer.getInt());
    }

    public String toString() {
        return String.format("\"%s\"", data);
    }
}
