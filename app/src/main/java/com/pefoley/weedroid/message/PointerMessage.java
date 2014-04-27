package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class PointerMessage extends Message {

    private String data;

    PointerMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.PTR);
        this.data = String.format("0x%s", MessageParser.parseString(buffer, (int) buffer.get()));
    }

    public String toString() {
        return data.equals("0x0") ? "null" : data;
    }
}
