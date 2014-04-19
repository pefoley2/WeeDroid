package com.pefoley.weedroid.app;

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
            w.sendCommand(new InitCommand("bob"));
            List<Message> m = w.processPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
