package com.pefoley.weedroid.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.pefoley.weedroid.command.TestCommand;
import com.pefoley.weedroid.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Network implements Runnable {

    private Context context;

    public Network(Context context) {
        this.context = context;

    }

    public void run() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String server = preferences.getString("server", "");
        int port = Integer.parseInt(preferences.getString("port", "8000"));
        try {
            Socket s = new Socket(server, port);
            WeeChatRelay w = new WeeChatRelay(s);
            w.connect(preferences.getString("password", ""));
            List<Message> m = w.processCommand(new TestCommand());
            for (Message q : m) {
                Log.e("WEE", String.format("%s: %s", q.getType(), q));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
