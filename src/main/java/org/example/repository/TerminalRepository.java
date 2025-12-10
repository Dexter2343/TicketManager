package org.example.repository;

import org.example.service.OwnerService;
import org.example.service.UserService;
import org.example.abstractions.User;

import java.util.Scanner;

public class TerminalRepository {

    private final UserService userService;
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository = new OwnerRepository();
    private final OwnerService ownerService = new OwnerService();
    private User loggedUser;

    public TerminalRepository() {
        this.userService = new UserService();
        this.userRepository = new UserRepository();
    }

    public void terminal() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (register, login, info, exit, buy, create, clear, delete, makeAdmin, makeOwner):  ");
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            }

            // Register
            if (command.equalsIgnoreCase("register")) {
                User newUser = userService.registerUser();
                userRepository.saveRegisterUser(newUser);
                System.out.println("Registration complete!");
            }

            // Login
            if (command.equalsIgnoreCase("login")) {
                User inputUser = userService.loginUser();
                User found = userRepository.checkLoginUser(inputUser);

                if (found != null) {
                    loggedUser = found;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed!");
                }
            }

            // Info
            if (command.equalsIgnoreCase("info")) {
                if (loggedUser != null) {
                    System.out.println("---- USER INFO ----");
                    System.out.println("Firstname: " + loggedUser.getFirstName());
                    System.out.println("Lastname: " + loggedUser.getLastName());
                    System.out.println("You fly from: " + loggedUser.getTicketFrom());
                    System.out.println("You fly to: " + loggedUser.getTicketTo());
                    System.out.println("Role: " + loggedUser.getRole());
                } else {
                    System.out.println("Login failed!");
                }
            }

            // Buy ticket
            if (command.equalsIgnoreCase("buy")) {
                if (loggedUser != null) {
                    userRepository.buyTicket(loggedUser);
                } else {
                    System.out.println("You must login first!");
                }
            }

            // Create flight
            if (command.equals("create")) {
                if (loggedUser != null && (loggedUser.getRole().equals("Admin") || loggedUser.getRole().equals("owner"))) {
                    User flight = ownerService.createFlight();
                    ownerRepository.saveFlight(flight);
                } else {
                    System.out.println("Error! You don't have permission to use this command or not logged in!");
                }
            }

            // Clear flight
            if (command.equals("clear")) {
                if(loggedUser != null &&
                        (loggedUser.getRole().equals("Admin") || loggedUser.getRole().equals("owner"))) {
                    int id = ownerService.clearFlight();
                    ownerRepository.clearFlight(id);
                } else{
                    System.out.println("Error! You don't have permission to use this command or not logged in!");
                }
            }

            // Delete user's ticket
            if (command.equals("delete")) {
                if(loggedUser != null &&
                        (loggedUser.getRole().equals("Admin") || loggedUser.getRole().equals("owner"))) {
                    int id = ownerService.deleteTicket();
                    ownerRepository.deleteUsersTicket(id);
                } else{
                    System.out.println("Error! You don't have permission to use this command or not loggedIn!");
                }
            }

            // Make Admin
            if (command.equalsIgnoreCase("makeAdmin")) {
                if(loggedUser != null &&
                        (loggedUser.getRole().equals("owner"))) {
                    User adm = ownerService.regAdmin();
                    ownerRepository.adminMaker(adm);
                } else {
                    System.out.println("You don't have permission to use this command or not logged in!");
                }
            }


            // Make Owner
            if (command.equalsIgnoreCase("makeOwner")) {
                if (!ownerRepository.checkRole("owner")) {
                    User own = ownerService.regOwner();
                    ownerRepository.createOwner(own);
                } else {
                    System.out.println("Owner already exists!");
                }
            }
        }
    }
}
