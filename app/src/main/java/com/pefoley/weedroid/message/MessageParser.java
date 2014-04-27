package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MessageParser {

    static void printArray(ByteBuffer buffer) {
        byte[] array = buffer.array();
        System.out.print(buffer.remaining() + " [");
        for (int i = buffer.position(); i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println("]");
    }

    static String parseString(ByteBuffer buffer, int length) {
        // Null string
        if (length == -1) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) buffer.get());
        }
        return builder.toString();
    }

    static MsgType getType(ByteBuffer buffer) {
        // Type is 3 chars
        return MsgType.valueOf(parseString(buffer, 3).toUpperCase());
    }

    public static List<Message> parseMessages(ByteBuffer buffer) {
        List<Message> messages = new ArrayList<Message>();
        // ID's length comes right before it
        String id = parseString(buffer, buffer.getInt());
        while (buffer.hasRemaining()) {
            MsgType type = getType(buffer);
            messages.add(Message.parseMessage(id, type, buffer));
        }
        return messages;
    }
}
