package com.pefoley.weedroid.command;

public class InfoListCommand extends Command {
    String name;
    String pointer = null;
    String[] arguments = null;

    public InfoListCommand(String name) {
        this.type = CommandType.INFOLIST;
        this.name = name;
    }

    public InfoListCommand(String name, String pointer) {
        this(name);
        this.pointer = pointer;
    }

    public InfoListCommand(String name, String pointer, String[] arguments) {
        this(name, pointer);
        this.arguments = arguments;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("infolist ");
        output.append(name);
        if (pointer != null) {
            output.append(" ").append(pointer);
        }
        if (arguments != null) {
            for (String s : arguments) {
                output.append(" ").append(s);
            }
        }
        return output.toString();
    }
}
