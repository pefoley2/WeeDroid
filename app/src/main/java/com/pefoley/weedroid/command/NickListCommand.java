package com.pefoley.weedroid.command;

public class NickListCommand extends Command {
    String pointer = null;

    public NickListCommand() {
        this.type = CommandType.NICKLIST;
    }

    public NickListCommand(String pointer) {
        this();
        this.pointer = pointer;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("nicklist");
        if (pointer != null) {
            output.append(" ").append(pointer);
        }
        return output.toString();
    }
}
