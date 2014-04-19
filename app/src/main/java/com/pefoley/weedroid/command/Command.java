package com.pefoley.weedroid.command;

import com.pefoley.weedroid.message.CharMessage;
import com.pefoley.weedroid.message.MsgType;

import java.nio.ByteBuffer;

public abstract class Command {
    protected CommandType type;

    public abstract String toString();
}
