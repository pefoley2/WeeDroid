package com.pefoley.weedroid.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.pefoley.weedroid.command.InfoCommand;
import com.pefoley.weedroid.command.InitCommand;
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
        Socket s;
        try {
            s = new Socket(server, port);
            WeeChatRelay w = new WeeChatRelay(s);
            w.sendCommand(new InitCommand(preferences.getString("password", "")));
            w.sendCommand(new InfoCommand("version"));
            List<Message> m = w.processPacket();
            if (m == null) {
                throw new IOException("Invalid password");
            }
            for (Message q : m) {
                Log.e("WEE", q.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
