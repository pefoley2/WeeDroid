package com.pefoley.weedroid.app;

import com.pefoley.weedroid.command.Command;
import com.pefoley.weedroid.command.InfoCommand;
import com.pefoley.weedroid.command.InitCommand;
import com.pefoley.weedroid.message.Message;
import com.pefoley.weedroid.message.MessageParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class WeeChatRelay {

    private InputStream input;
    private OutputStream output;

    WeeChatRelay(Socket s) throws IOException {
        this.input = s.getInputStream();
        this.output = s.getOutputStream();
    }

    boolean connect(String password) throws IOException {
        sendCommand(new InitCommand(password));
      /* We need to check if we can send a command after sending the password
       * If we can't, assume the password was wrong.
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
        output.write(String.format("%s\n", command).getBytes());
    }

    private List<Message> processPacket() throws IOException {
        byte[] data, array = new byte[5];
        int numbytes = 8192;
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
        int size, offset = 0;
        do {
            // Prevent overflow
            length = numbytes + offset < data.length ? numbytes : data.length - offset;
            size = input.read(data, offset, length);
            offset += size;
        } while (size > 0);
        // Compression
        if (buffer.get() != 0) {
            Inflater inflater = new Inflater();
            inflater.setInput(data);
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            try {
         /* We don't know how much output there will be */
                byte[] inflated = new byte[numbytes];
                while (inflater.getRemaining() > 0) {
                    size = inflater.inflate(inflated);
                    tmp.write(inflated, 0, size);
                }
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
            // Get only the inflated bytes
            data = tmp.toByteArray();
            inflater.end();
        }


        return MessageParser.parseMessages(ByteBuffer.wrap(data).asReadOnlyBuffer());
    }
}
