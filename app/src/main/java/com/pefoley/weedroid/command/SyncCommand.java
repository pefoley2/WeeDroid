package com.pefoley.weedroid.command;

public class SyncCommand extends Command {
    String name;

    public SynCommand(String name) {
        this.type = CommandType.SYNC;
        this.name = name;
    }


    public String toString() {
        return String.format("sync %s", name);
    }
}
