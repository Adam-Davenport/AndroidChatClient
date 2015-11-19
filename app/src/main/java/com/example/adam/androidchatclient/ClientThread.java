package com.example.adam.androidchatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {

    Thread runner;

    public ClientThread(){
        runner = new Thread(this);
        runner.start();
    }
    public void run() {
        DataInputStream in;
        DataOutputStream out;
        Socket socket;

        //Try Catch to handle any exceptions from sockets
        try
        {
            //Creating a socket on the local host with port 8189
            socket = new Socket("10.0.0.3", 8189);
            //Implementing a way to output text to the server
            out = new DataOutputStream(socket.getOutputStream());
            //Input to the client
            in = new DataInputStream(socket.getInputStream());
            //After the connection is initiate the first message is sent.
            out.writeUTF("Connection Initiated");
            System.out.println("Message Sent");
            //Prints the input to the console
            System.out.println(in.readUTF());
            //Keeps looking for input
            boolean done = false;
            //Infinite loop currently not able to be closed without specifically entering the exit command
            //Will cause errors
            while (!done)
            {
                //The client waits for the next line from the server
                String lineIn = in.readUTF();
                System.out.println(lineIn);

            }
            //Closes the client
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
