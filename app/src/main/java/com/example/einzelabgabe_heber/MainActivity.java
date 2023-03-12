package com.example.einzelabgabe_heber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private Button button;
    private TextView textView2;
    private Button button2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button2);

        /* Nimmt die angegebene Matrikelnummer aus dem editText speichert es in den String input und created einen EBThread mit dem
        übergebenen Input.
        mit start() wird der Thread gestartet. Mit join wird auf das Ende des threads gewartet sonst eine Error geschickt.
         */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = findViewById(R.id.editText);
                String input = editText.getText().toString();
                EBThread t = new EBThread(input);
                t.start();
                try {
                    t.join();
                    textView.setText(t.getResponse());
                } catch (InterruptedException e) {
                    textView.setText("Error");
                }

            }
        });

        /*
        Button für aufgabe 2.2
        speichert wieder den Wert vom eingabefeld als input und im Ausgabefeld wird die funktion calculate aufgerufen,
        die einen String zurückgibt und diesen dann ins zweite textView speichert.
         */

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = findViewById(R.id.editText);
                String input = editText.getText().toString();
                String output = calculate(input);
                textView2.setText(output);
            }
        });
    }

    /* 11739932%7 = 1
    * Alternierende	Quersumme	bilden	und	ausgeben,	ob
        diese	gerade	oder	ungerade	ist
        *
        *
        * Bei einer alternatierenden Quersumme werden die einzelnen Ziffern einer Zahl hintereinander abwechselnd subtrahiert und addiert.
        * Dabei darf von vorne oder hinten begonnen werden.
        *
        * in meinem Beispiel wird von hinten begonnen
    */


    /*
    Funktion zur ermittlung von der alternierenden Quersumme
     */
    public String calculate(String s) {

        /*
        Übergebener String wird in eine Zahl geparsed.
        Summe für das abspeichern der Zwischensumme
        Digit für die Ermittlung der einzelnen Zahlen
        i als count um abwechselnt + und - rechnen zu können
        Response als Antwort String
         */
        int number = Integer.parseInt(s);
        int sum = 0;
        int digit;
        int i = 0;
        String response;

        while(number != 0) {
            digit = number % 10;
            if (i == 0) {
                sum = digit;
            } else if (i % 2 == 1) {
                sum += digit;
            } else {
                sum -= digit;
            }
            number = number / 10;
            i++;
        }

        if (sum % 2 == 0) {
            response = "Die alternierende Quersumme ist gerade (Summe: " + sum + ")";
        } else {
            response = "Die alternierende Quersumme ist ungerade (Summe: " + sum + ")";
        }

        return response;

    }
}
