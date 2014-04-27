package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ArrayMessage extends Message {

    private Message[] data;
    private MsgType arrtype;

    ArrayMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.ARR);
        arrtype = MessageParser.getType(buffer);

        int length = buffer.getInt();
        data = new Message[length];
        for (int i = 0; i < length; i++) {
            data[i] = Message.parseMessage(id, arrtype, buffer);
        }
    }

    public String toString() {
        return Arrays.toString(data);
    }
}
