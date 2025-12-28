package models;

import java.util.Scanner;

public class InputHandler {
    String username;
    Scanner sc = new Scanner(System.in);

    public String getUsername() {
        System.out.println("Please enter the username you want to search history off:");
        username = sc.nextLine();
        return username;
    }
}
