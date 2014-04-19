package com.pefoley.weedroid.app;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MessageParser {
    static String parseString(ByteBuffer buffer, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(buffer.getChar());
        }
        return builder.toString();
    }

    static List<Message> parseMessages(ByteBuffer buffer) {
        List<Message> messages = new ArrayList<Message>();
        while (buffer.hasRemaining()) {
            // ID's length comes right before it
            String id = parseString(buffer, buffer.getInt());
            // Type is 3 chars
            MsgType type = MsgType.valueOf(parseString(buffer, 3).toUpperCase());
            messages.add(new Message(id, type, buffer));
        }
        return messages;
    }
}
