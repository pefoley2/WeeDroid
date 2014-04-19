package com.pefoley.weedroid.app;

import java.nio.ByteBuffer;

enum MsgType {
    CHR, // Signed char
    INT, // Signed integer
    LON, // Signed long integer
    STR, // String
    BUF, // Buffer of bytes
    PTR, // Pointer
    TIM, // Time
    HTB, // Hashtable
    HDA, // Hdata content
    INF, // Info: name + content
    INL, // Infolist content
    ARR // Array of objects
}

public class Message {

    String id;
    MsgType type;
    Object data;

    Message(String id, MsgType type, ByteBuffer buffer) throws Exception {
        this.id = id;
        this.type = type;
        switch (type) {
            default:
                throw new Exception("Not implemented.");
        }
    }
}
