package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InfoListMessage extends Message {

    private String name;
    private List<InfoList> data;

    InfoListMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.INL);
        name = MessageParser.parseString(buffer, buffer.getInt());
        int count = buffer.getInt();
        data = new ArrayList<InfoList>(count);
        for (int i = 0; i < count; i++) {
            data.add(new InfoList(buffer));
        }
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (InfoList i : data) {
            output.append(i);
        }
        return output.toString();
    }

    private class InfoList {

        private Map<String, Message> data;

        InfoList(ByteBuffer buffer) {
            int count = buffer.getInt();
            // Preserve ordering
            data = new LinkedHashMap<String, Message>(count);
            for (int i = 0; i < count; i++) {
                String name = MessageParser.parseString(buffer, buffer.getInt());
                MsgType type = MessageParser.getType(buffer);
                data.put(name, Message.parseMessage(id, type, buffer));
            }
        }

        public String toString() {
            StringBuilder output = new StringBuilder();
            for (String s : data.keySet()) {
                output.append(s).append(": ").append(data.get(s)).append("\n");
            }
            return output.toString();
        }

    }

}
