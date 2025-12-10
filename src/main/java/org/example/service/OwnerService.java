package org.example.service;

import org.example.abstractions.User;
import java.util.Scanner;

public class OwnerService {
    public User regAdmin() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Welcome to owner panel!");
        System.out.println("Enter first name new admin: ");
        user.setFirstName(sc.nextLine());
        System.out.println("Enter last name new admin: ");
        user.setLastName(sc.nextLine());
        System.out.println("Enter password new admin: ");
        user.setPassword(sc.nextLine());

        user.setRole("Admin");
        System.out.println("Admin created successfully!");

        return user;
    }


    public User regOwner() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter owner first name:");
        user.setFirstName(sc.nextLine());

        System.out.println("Enter owner last name:");
        user.setLastName(sc.nextLine());

        System.out.println("Enter owner password:");
        user.setPassword(sc.nextLine());

        user.setRole("owner");

        return user;
    }

    public User createFlight() {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter where Start: ");
        user.setTicketFrom(sc.nextLine());

        System.out.println("Enter where heading: ");
        user.setTicketTo(sc.nextLine());

        System.out.println("Enter status: ");
        user.setStatus(sc.nextLine());

        System.out.println("Successfully!");
        return user;
    }

    public int clearFlight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter flight ID to delete: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int deleteTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID to delete: ");
        return Integer.parseInt(sc.nextLine());
    }
}
