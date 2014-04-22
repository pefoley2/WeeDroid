package com.pefoley.weedroid.app;

import com.pefoley.weedroid.command.Command;
import com.pefoley.weedroid.message.Message;
import com.pefoley.weedroid.message.MessageParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class WeeChatRelay {

    private Socket socket;


    WeeChatRelay(Socket s) {
        this.socket = s;
    }

    void sendCommand(Command command) throws IOException {
        String output = command + "\n";
        socket.getOutputStream().write(output.getBytes());
    }

    boolean isClosed() {
        return socket.isClosed();
    }

    List<Message> processPacket() throws IOException {
        byte[] data, array = new byte[5];
        int length;
        ByteBuffer buffer;
        InputStream input = socket.getInputStream();
        if (input.read(array) < 0) {
            return null;
        }
        buffer = ByteBuffer.wrap(array);
        length = buffer.getInt();
        // We don't need the length and compression fields.
        data = new byte[length-5];
        // Compression
        if (buffer.get() != 0) {
            input = new GZIPInputStream(input);
        }
        input.read(data);
        return MessageParser.parseMessages(ByteBuffer.wrap(data).asReadOnlyBuffer());
    }
}
