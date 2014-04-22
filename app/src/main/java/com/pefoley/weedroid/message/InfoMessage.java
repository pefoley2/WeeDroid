package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class InfoMessage extends Message {

    private String name;
    private String value;

    InfoMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.INF);
        this.name = MessageParser.parseString(buffer, buffer.getInt());
        this.value = MessageParser.parseString(buffer, buffer.getInt());
    }

    public String toString() {
        return String.format("%s: %s", name, value);
    }
}
