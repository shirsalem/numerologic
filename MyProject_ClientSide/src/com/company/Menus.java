package com.company;

import java.io.OutputStream;
import java.sql.Struct;
import java.util.Scanner;

//I created this class in order to make the code cleaner and readable. Here, I did a little menu for the user to be able to generate more than one name, and also to be able to exit the app and get out. This is also an abstract method.

public abstract class Menus {

    private static int readIntegerFromConsole(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();// Asking for action number.
        int x = -1;
        try {
            x = Integer.valueOf(input);//Getting the value of the user input.
        }catch (Exception ex) {
            System.out.println("This is not an integer!\n");//If the user choose a char that is not an integer,
            return printMenu();// I re ask him for a choice.
        }
        return x;
    }


    public static int printMenu() {//Printing the menu to the user
        System.out.println("Choose what do you want to do?:");
        System.out.println("1. Check a new name");
        System.out.println("2. Exit the app");
        int choice = readIntegerFromConsole();//Reading the user choice from the last method.
        if (choice  != 1 && choice != 2) {// Choice muse be 1 or 2, cant be any other number (unless I decide otherwise)
            System.out.println("Sorry, invalid choice\n");//If the user do choose a number that dose not appears as an option, I sed to him that his choice was wrong.
            return printMenu();// And printing the menu again.
        }
        return choice;// Do what the user choose
    }
}

