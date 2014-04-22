package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class CharMessage extends Message {

    private char data;

    CharMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.CHR);
        this.data = buffer.getChar();
    }

    public String toString() {
        return String.valueOf(data);
    }
}
