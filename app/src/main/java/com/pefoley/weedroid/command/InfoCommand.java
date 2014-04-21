package com.pefoley.weedroid.command;

public class InfoCommand extends Command {
    String name;

    public InfoCommand(String name) {
        this.type = CommandType.INFO;
        this.name = name;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder("info ");
        builder.append(name);
        return builder.toString();
    }
}
