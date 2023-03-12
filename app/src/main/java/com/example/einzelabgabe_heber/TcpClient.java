package com.example.einzelabgabe_heber;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static final int SERVER_PORT		= 53212;
    public static final String SERVER_STRING = "se2-isys.aau.at";

    public static void main(String[] args) throws IOException {
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket(SERVER_STRING, SERVER_PORT);

        DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader is_Server = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String sent = is.readLine();

        os.writeBytes(sent + '\n');

        String modified = is_Server.readLine();

        System.out.println("Response from the Server:" + modified);

        clientSocket.close();
    }




}





