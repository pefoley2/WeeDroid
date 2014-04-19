package com.pefoley.weedroid.app;

enum CommandType {
    INIT, // Initialize connection with relay
    HDATA, // Request a hdata
    INFO, // Request an info
    INFOLIST, // Request an infolist
    NICKLIST, // Request an nicklist
    INPUT, // Send data to a buffer
    SYNC, // Synchronize buffer(s)
    DESYNC, // Desynchronize buffer(s)
    QUIT // Disconnect from relay
}

public class Command {
}
