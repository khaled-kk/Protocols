package com.example.mileston0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class client extends AppCompatActivity {
    EditText text;
    Button button,button2;
    Socket socket=null;
    static String massage= null;
    static DataOutputStream outToServer= null;
    static BufferedReader inFromServer= null;
    static String server= null;
    String hostName = "192.168.43.226";
    int portNumber = 8888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text= findViewById(R.id.editText);
        button= findViewById(R.id.button);
        button2= findViewById(R.id.button2);

        button2.setOnClickListener(v -> {
            try {
                socket=new Socket(hostName,portNumber);
                Toast.makeText(client.this, "Connected", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button.setOnClickListener(view -> {
            massage=text.getText().toString();
            try {
                outToServer = new DataOutputStream(socket.getOutputStream());
                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outToServer.writeBytes(massage + '\n');
                server = inFromServer.readLine();
                Toast.makeText(client.this, "FROM SERVER: " +server,Toast.LENGTH_SHORT).show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}