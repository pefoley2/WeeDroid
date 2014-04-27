package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class IntMessage extends Message {

    private int data;

    IntMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.INT);
        this.data = buffer.getInt();
    }

    public String toString() {
        return String.valueOf(data);
    }
}
