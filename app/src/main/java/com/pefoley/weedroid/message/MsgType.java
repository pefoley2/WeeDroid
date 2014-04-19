package com.pefoley.weedroid.message;

public enum MsgType {
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
