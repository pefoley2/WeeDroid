package com.pefoley.weedroid.command;

import android.text.TextUtils;

public class HDataCommand extends Command {
    String path;
    String[] keys = null;

    public HDataCommand(String path) {
        this.type = CommandType.HDATA;
        this.path = path;
    }

    public HDataCommand(String path, String[] keys) {
        this(path);
        this.keys = keys;
    }

    public HDataCommand(String path, String key) {
        this(path, new String[]{key});
    }


    public String toString() {
        StringBuilder output = new StringBuilder("hdata");
        output.append(" ").append(path).append(" ");
        if (keys != null) {
            output.append(TextUtils.join(",", keys));
        }
        return output.toString();
    }
}
