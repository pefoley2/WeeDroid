package com.pefoley.weedroid.command;

public enum CommandType {
    INIT, // Initialize connection with relay
    HDATA, // Request a hdata
    INFO, // Request an info
    INFOLIST, // Request an infolist
    NICKLIST, // Request an nicklist
    INPUT, // Send data to a buffer
    SYNC, // Synchronize buffer(s)
    DESYNC, // Desynchronize buffer(s)
    TEST, // Testing
    PING, // Ping
    QUIT // Disconnect from relay
}
