package com.pefoley.weedroid.message;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

public class HDataMessage extends Message {

    private String hpath;
    private HData[] data;
    private Map<String, MsgType> keymap;

    HDataMessage(String id, ByteBuffer buffer) {
        super(id, MsgType.HDA);
        hpath = MessageParser.parseString(buffer, buffer.getInt());
        String[] keys = MessageParser.parseString(buffer, buffer.getInt()).split(",");
        keymap = new LinkedHashMap<String, MsgType>();
        for (String s : keys) {
            String[] tmp = s.split(":");
            keymap.put(tmp[0], MsgType.valueOf(tmp[1].toUpperCase()));
        }
        int count = buffer.getInt();
        data = new HData[count];

        for (int i = 0; i < count; i++) {
            data[i] = new HData(buffer);
        }

    }

    public String toString() {
        StringBuilder output = new StringBuilder(hpath);
        output.append("\n");
        for (HData h : data) {
            output.append(" ").append(h);
        }
        return output.toString();
    }

    private class HData {
        PointerMessage[] pointers;
        Map<String, Message> data;

        HData(ByteBuffer buffer) {
            // One pointer for each element in path
            int numpointers = hpath.split("/").length;
            pointers = new PointerMessage[numpointers];

            for (int i = 0; i < numpointers; i++) {
                pointers[i] = new PointerMessage(id, buffer);
            }
            data = new LinkedHashMap<String, Message>(keymap.size());

            for (String s : keymap.keySet()) {
                data.put(s, Message.parseMessage(id, keymap.get(s), buffer));
            }
        }

        public String toString() {
            StringBuilder output = new StringBuilder();
            for (PointerMessage p : pointers) {
                output.append(p).append(" ");
            }
            for (String s : data.keySet()) {
                output.append(s).append(": ").append(data.get(s)).append(" ");
            }
            output.append("\n");
            return output.toString();
        }
    }
}
