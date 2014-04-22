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
        StringBuilder builder = new StringBuilder("init ");
        builder.append("password=").append(password);
        if (compression) {
            builder.append(",compression=zlib");
        }
        return builder.toString();
    }
}
