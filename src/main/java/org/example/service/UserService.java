package org.example.service;

import org.example.abstractions.User;
import java.util.Scanner;

public class UserService {

    public User ticketReg() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter firstname:");
        user.setFirstName(sc.nextLine());

        System.out.println("Enter lastname:");
        user.setLastName(sc.nextLine());

        System.out.println("Enter ticket from:");
        user.setTicketFrom(sc.nextLine());

        System.out.println("Enter ticket to:");
        user.setTicketTo(sc.nextLine());

        System.out.println("Order complete!");
        return user;
    }

    public User registerUser() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter firstname:");
        user.setFirstName(sc.nextLine());

        System.out.println("Enter lastname:");
        user.setLastName(sc.nextLine());

        System.out.println("Enter your password:");
        user.setPassword(sc.nextLine());

        System.out.println("Registration successful!");
        return user;
    }

    public User loginUser() {
        Scanner sc = new Scanner(System.in);
        User user = new User();


        System.out.println("Enter firstname: ");
        user.setFirstName(sc.nextLine());

        System.out.println("Enter lastname: ");
        user.setLastName(sc.nextLine());

        System.out.println("Enter your password: ");
        user.setPassword(sc.nextLine());

        return user;
    }




}
