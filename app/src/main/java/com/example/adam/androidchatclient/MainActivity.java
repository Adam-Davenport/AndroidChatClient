package com.example.adam.androidchatclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connect(View v)
    {
        ClientThread thread = new ClientThread();
        thread.run();
    }

    public void showText(String s)
    {
        EditText editText = (EditText) findViewById(R.id.chatPane);
        editText.append(s);
    }
}
