package com.example.einzelabgabe_heber;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EBThread extends Thread{

    public static final int SERVER_PORT		= 53212;
    public static final String SERVER_STRING = "se2-isys.aau.at";
    String input;
    String response;

    public EBThread(String input) {
        this.input = input;
    }

    public void run() {
        try {

            Socket clientSocket = new Socket(SERVER_STRING, SERVER_PORT);

            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            os.writeBytes(input + '\n');

            this.response = is.readLine();

            clientSocket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
