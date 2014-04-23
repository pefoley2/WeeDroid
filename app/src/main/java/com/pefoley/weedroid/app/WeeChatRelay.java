package com.pefoley.weedroid.app;

import com.pefoley.weedroid.command.Command;
import com.pefoley.weedroid.command.InfoCommand;
import com.pefoley.weedroid.command.InitCommand;
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
    private InputStream input;

    WeeChatRelay(Socket s) throws IOException {
        this.socket = s;
        this.input = s.getInputStream();
    }

    boolean connect(String password) throws IOException {
        sendCommand(new InitCommand(password));
        /* We need to check if we can send a command after sending the password
        If we can't, assume the password was wrong.
         */
        // FIXME: check the version and bail if it's too old.
        sendCommand(new InfoCommand("version"));
        List<Message> m = processPacket();
        return m != null;
    }

    List<Message> processCommand(Command command) throws IOException {
        sendCommand(command);
        return processPacket();
    }

    private void sendCommand(Command command) throws IOException {
        String output = command + "\n";
        socket.getOutputStream().write(output.getBytes());
    }

    private List<Message> processPacket() throws IOException {
        byte[] data, array = new byte[5];
        int length;
        ByteBuffer buffer;
        /* Connection lost */
        if (input.read(array) < 0) {
            return null;
        }
        buffer = ByteBuffer.wrap(array);
        length = buffer.getInt();
        // We don't need the length and compression fields.
        data = new byte[length - 5];
        // Compression
        if (buffer.get() != 0) {
            input = new GZIPInputStream(input);
        }
        input.read(data);
        return MessageParser.parseMessages(ByteBuffer.wrap(data).asReadOnlyBuffer());
    }
}
