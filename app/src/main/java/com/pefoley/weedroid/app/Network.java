package com.pefoley.weedroid.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.pefoley.weedroid.command.HDataCommand;
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
            /*
            (initialinfolist) infolist hotlist
            (listbuffers) hdata buffer:gui_buffers(*) number,full_name,short_name,type,title,nicklist,local_variables,notify
             */
            List<Message> list = w.processCommand(new HDataCommand("buffer:gui_buffers(*)", "full_name,short_name,type,title,nicklist,local_varibles"));
            for (Message m : list)
                for (String str : m.toString().split("\n")) {
                Log.e("WEE", str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
