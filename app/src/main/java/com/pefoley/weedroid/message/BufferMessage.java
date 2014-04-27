package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;

public class BufferMessage extends Message {

    private byte[] data;

    BufferMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.BUF);
        int length = buffer.getInt();
        if (length == -1) {
            this.data = null;
        } else {
            this.data = new byte[length];
            for (int i = 0; i < length; i++) {
                this.data[i] = buffer.get();
            }
        }
    }

    public String toString() {
        return data == null ? "null" : new String(data);
    }
}
