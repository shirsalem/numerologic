package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    public static final int PORT = 3000;///Port that will be the same at the Server and at the Client

    public static void main(String[] args) {
        startServerSocket();//I separated this method from the main in order the make the code looks cleaner and readable.
    }

    public static void startServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {//The while loop is always running and searching for client to connect unless I stop the Server.
                System.out.println("Waiting for incoming communication");//Show that the Server is working.
                Socket socket = serverSocket.accept();//The moment the Server is accepting the client.
                System.out.println("Client connected");//Show that the Server is working.
                new ClientThread(socket).start();//Starting this method.
            }
        } catch (IOException e) {//Ty & Catch for the program to be able continue running.
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
