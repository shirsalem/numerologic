package com.company;

import javax.xml.soap.Name;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientThread extends Thread {

    public static final int NAME = 100;
//    public static final int PORT = 3000;
//    public static final String HOST = "127.0.0.1";

    //Choosing constants variables that will show the 9 results I have for any name.
    public static final int RESULT1 = 101;//Result of numerological number 1
    public static final int RESULT2 = 102;//Result of numerological number 2
    public static final int RESULT3 = 103;//Result of numerological number 3
    public static final int RESULT4 = 104;//Result of numerological number 4
    public static final int RESULT5 = 105;//Result of numerological number 5
    public static final int RESULT6 = 106;//Result of numerological number 6
    public static final int RESULT7 = 107;//Result of numerological number 7
    public static final int RESULT8 = 108;//Result of numerological number 8
    public static final int RESULT9 = 109;//Result of numerological number 9

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override//Overriding the RUN method
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            while (true) {
                int action = inputStream.read();
                switch (action) {
                    case NAME:
                        System.out.println("Sum of letters: ");
                        userName();
                        break;
                }
            }
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
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void userName() throws IOException{
        byte[] arrayLengthByte = new byte[4];
        int actuallyRead = inputStream.read(arrayLengthByte);
        if(actuallyRead != 4)
            return;
        int arrayLength = ByteBuffer.wrap(arrayLengthByte).getInt();
        byte[] array = new byte[arrayLength];
        actuallyRead = inputStream.read(array);
        if (actuallyRead != arrayLength)
            return;
        char[] letters = new char[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            letters[i] = (char) array[i];
        }
lettersName(letters);
    }

    public void lettersName(char[] letters) {

        int letterSum = 0;
        for (int ii = 0; ii < letters.length; ii++) {
            letterSum++;
        }
        int summeryOfLetters = 0;

        for (int iii = 0; iii < letterSum; iii++) {
            summeryOfLetters += (int) letters[iii];
        }
        System.out.println(summeryOfLetters);
            //Here the Server calculate the summery of the letters, and sent the answer + the RESULT number to the Client.
            //for each summery of letters the server 'knows' what description to show.
            if (summeryOfLetters >= 1 && summeryOfLetters <= 300)
               sendResult(RESULT1);
            else if (summeryOfLetters > 300 && summeryOfLetters <= 400)
                sendResult(RESULT2);
            else if (summeryOfLetters > 400 && summeryOfLetters <= 500)
                sendResult(RESULT3);
            else if (summeryOfLetters > 500 && summeryOfLetters <= 600)
                sendResult(RESULT4);
            else if (summeryOfLetters > 600 && summeryOfLetters <= 700)
                sendResult(RESULT5);
            else if (summeryOfLetters > 700 && summeryOfLetters <= 800)
                sendResult(RESULT6);
            else if (summeryOfLetters > 800 && summeryOfLetters <= 900)
                sendResult(RESULT7);
            else if (summeryOfLetters > 900 && summeryOfLetters <= 1000)
                sendResult(RESULT8);
            else if (summeryOfLetters > 1000)
                sendResult(RESULT9);
        }

    private void sendResult(int action){
        try {
            outputStream.write(action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
