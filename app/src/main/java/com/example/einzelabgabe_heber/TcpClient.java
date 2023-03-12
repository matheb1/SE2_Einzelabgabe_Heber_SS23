package com.example.einzelabgabe_heber;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static final int SERVER_PORT		= 53212;
    public static final String SERVER_STRING = "se2-isys.aau.at";

    protected String execute(String... strings) {

        String message = strings[0];
        String response = null;

        try {
            Socket socket = new Socket(SERVER_STRING, SERVER_PORT);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            byte[] messageBytes = message.getBytes();
            os.write(messageBytes, 0, messageBytes.length);
            os.flush();

            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            response = new String(buffer, 0, bytesRead);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }




}
