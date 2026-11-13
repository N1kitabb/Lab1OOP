package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        ConsoleInput input = new ConsoleInput();
        MainMenu menu = new MainMenu(input);
        menu.printMainMenu();
    }
}