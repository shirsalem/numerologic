package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

//OnFirstRun is a method that ask fo a name after the user has choose at the menu that he wants to 'check a new name', this is an abstract method because it dose not have any object.
public abstract class OnFirstRun {

    public static final int NAME = 100;

    public static void askForUserName(OutputStream outputStream, InputStream inputStream) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name in order to get your numerology number:");//Asking to user for a name.
        String letters = scanner.nextLine();//Getting the name from the user.
        try {
            //Here, I turning the Char Array into Bytes Array, because this is the only way the Server could get the this data from the Client.
            outputStream.write(NAME);
            byte[] bytes = new byte[4];
            ByteBuffer.wrap(bytes).putInt(letters.length());
            outputStream.write(bytes);
            outputStream.write(letters.getBytes());
            int action = inputStream.read();//After I defined the constants and their limits, I wrote a short information to explain of each number definition to make it more real.
            printResult(action - 100);//This method print the RESULT. I wrote action - 100 because this is connected to the files i have and every name file end with a number the define its result, which is 1,2,3...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method read the file size, path and Strings according to what the user ask to print.
    public static void printResult(int num){
        InputStream inputStream = OnFirstRun.class.getResourceAsStream("results\\Result" + num + ".txt");
        int read = 0;
        byte[] bytes = new byte[1024];
        try {
            while ((read = inputStream.read(bytes))!= -1){
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

