package com.example.adam.androidchatclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Socket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connect(View v)
    {
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
    }

    public void sendMessage(View v)
    {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("TEST");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showText(String s)
    {
        EditText editText = (EditText) findViewById(R.id.chatPane);
        editText.append(s);
    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                client = new Socket("10.0.0.3", 8189); // connect to the server
                DataInputStream in = new DataInputStream(client.getInputStream());
                while(true) {
                    in.readUTF();
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
