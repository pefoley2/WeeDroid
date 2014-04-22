package com.pefoley.weedroid.app;

import android.util.Log;

import com.pefoley.weedroid.command.InfoCommand;
import com.pefoley.weedroid.command.InitCommand;
import com.pefoley.weedroid.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Network implements Runnable {

    public void run() {
        Socket s = null;
        try {
            //FIXME: get from settings.
            s = new Socket("pefoley.com", 8001);
            WeeChatRelay w = new WeeChatRelay(s);
            w.sendCommand(new InitCommand("fakepassword"));
            w.sendCommand(new InfoCommand("version"));
            List<Message> m = w.processPacket();
            if (m == null) {
                throw new IOException("Invalid password");
            }
            for(Message q:m) {
                Log.e("WEE", q.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
