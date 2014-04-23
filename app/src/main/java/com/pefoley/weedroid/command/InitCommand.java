package com.pefoley.weedroid.command;

public class InitCommand extends Command {
    // FIXME: This is a horrible idea.
    String password;
    boolean compression = false;

    public InitCommand(String password) {
        this.type = CommandType.INIT;
        this.password = password;
    }

    public InitCommand(String password, boolean compression) {
        this(password);
        this.compression = compression;
    }

    public String toString() {
        String output = String.format("init password=%s", password);
        if (compression) {
            output += ",compression=zlib";
        }
        return output;
    }
}
