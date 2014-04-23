package com.pefoley.weedroid.command;

public class TestCommand extends Command {

    public TestCommand() {
        this.type = CommandType.TEST;
    }


    public String toString() {
        return "test";
    }
}
