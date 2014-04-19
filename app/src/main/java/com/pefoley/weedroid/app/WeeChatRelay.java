package com.pefoley.weedroid.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

public class WeeChatRelay {

    private Socket socket;
    private OutputStreamWriter output;
    private InputStream input;

    WeeChatRelay(Socket s) {
        this.socket = s;
        try {
            this.output = new OutputStreamWriter(s.getOutputStream());
            this.input = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void init(String password) throws IOException {
        output.write("init password="+password);

    }
    void getPacket() {
        byte[] array = new byte[5];
        int length;
        boolean compression;
        ByteBuffer buffer;
        try {
        input.read(array);
            buffer = ByteBuffer.wrap(array);
            length = buffer.getInt();
            compression = buffer.get() != 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
