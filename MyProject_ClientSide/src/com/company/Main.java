package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//Client side

public class Main {

    //Choosing constants variables:

    public static final int PORT = 3000;//Port that will be the same at the Server and at the Client
    public static final String HOST = "127.0.0.1";//Host- right now is send it to my self

    //Choosing constants variables that will show the 9 results I have for any name.
//    public static final int RESULT1 = 101;//Result of numerological number 1
//    public static final int RESULT2 = 102;//Result of numerological number 2
//    public static final int RESULT3 = 103;//Result of numerological number 3
//    public static final int RESULT4 = 104;//Result of numerological number 4
//    public static final int RESULT5 = 105;//Result of numerological number 5
//    public static final int RESULT6 = 106;//Result of numerological number 6
//    public static final int RESULT7 = 107;//Result of numerological number 7
//    public static final int RESULT8 = 108;//Result of numerological number 8
//    public static final int RESULT9 = 109;//Result of numerological number 9

    private static boolean run = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Scanner - in order to get data from the user.
        Socket socket = null;//Socket - in order to transport the data between the Server and the Client.
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            socket = new Socket(HOST, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            System.out.println("You are connected to the server!\n");
            System.out.println("Welcome!\n");
            while (run) {
                switch(Menus.printMenu()){
                    case 1:
                        //OnFirstRun.printResult();
                        OnFirstRun.askForUserName(outputStream,inputStream);//This is the main method, I call it after the Server is already know the summery of the letters of the name that the user inserted.
                        break;
                    case 2:
                        System.out.println("Disconnecting...");
                        run = false;
                        break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



