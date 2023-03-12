package com.example.einzelabgabe_heber;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EBThread extends Thread{

    public static final int SERVER_PORT		= 53212;
    public static final String SERVER_STRING = "se2-isys.aau.at";
    public String input;
    public String response;

    //Konstruktor + getter und Setter (nicht gebrauchte gelöscht)
    public EBThread(String input) {
        this.input = input;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public void run() {
        try {

            //client socket wird erstellt, connect zum Server
            Socket clientSocket = new Socket(SERVER_STRING, SERVER_PORT);

            //Output stream, schickt input zum Server
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

            //Response vom Server
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //schickt matrikelnummer zum server "\n" fürs ende
            os.writeBytes(input + '\n');

            //Speichert die antwort vom Server in den String response
            this.response = is.readLine();

            //schließt den Socket wieder
            clientSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
