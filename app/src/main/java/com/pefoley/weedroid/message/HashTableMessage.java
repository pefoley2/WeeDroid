package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashTableMessage extends Message {

    private Map<Message, Message> data;

    HashTableMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.HTB);
        data = new LinkedHashMap<Message, Message>();
        MsgType keytype = MessageParser.getType(buffer);
        MsgType valuetype = MessageParser.getType(buffer);
        int count = buffer.getInt();
        for (int i = 0; i < count; i++) {
            Message key = Message.parseMessage(id, keytype, buffer);
            data.put(key, Message.parseMessage(id, valuetype, buffer));
        }
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Message m : data.keySet()) {
            output.append(m).append(": ").append(data.get(m));
        }
        return output.toString();
    }
}
