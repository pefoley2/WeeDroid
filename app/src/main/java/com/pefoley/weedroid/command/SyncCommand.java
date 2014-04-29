package com.pefoley.weedroid.command;

import android.text.TextUtils;

public class SyncCommand extends Command {
    String[] buffers;
    String[] options = null;

    public SyncCommand(String[] buffers) {
        this.type = CommandType.SYNC;
        this.buffers = buffers;
    }

    public SyncCommand(String buffer) {
        this(new String[]{buffer});
    }

    public SyncCommand(String[] buffers, String[] options) {
        this(buffers);
        this.options = options;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("sync");
        output.append(" ");
        output.append(TextUtils.join(",", buffers));
        if (options != null) {
            output.append(" ");
            output.append(TextUtils.join(",", options));
        }
        return output.toString();
    }
}
