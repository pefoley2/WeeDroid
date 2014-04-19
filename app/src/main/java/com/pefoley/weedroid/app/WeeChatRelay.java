package com.pefoley.weedroid.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;

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
        output.write("init password=" + password);

    }

    void getPacket() throws IOException {
        byte[] data, array = new byte[5];
        int length;
        ByteBuffer buffer;
        input.read(array);
        buffer = ByteBuffer.wrap(array);
        length = buffer.getInt();
        // We don't need the length and compression fields.
        data = new byte[length-5];
        // Compression
        if (buffer.get() != 0) {
            input = new GZIPInputStream(input);
        }
        input.read(data);
        MessageParser.parseMessages(ByteBuffer.wrap(data).asReadOnlyBuffer());
    }
}
