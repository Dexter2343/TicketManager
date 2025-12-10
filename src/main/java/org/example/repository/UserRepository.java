package org.example.repository;

import org.example.abstractions.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserRepository {

  private   String db = "jdbc:postgresql://localhost:5432/Tickets";
  private   String username = "postgres";
  private   String password = "1234";

  // buy ticket and add to DB
    public User buyTicket(User user) {
        Scanner sc = new Scanner(System.in);
        String formatter = "HH:mm dd-MM-yyyy";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatter);
        String date = now.format(dateFormatter);


        System.out.println("Enter ticket from: ");
        String from = sc.nextLine();

        System.out.println("Enter ticket to: ");
        String to = sc.nextLine();


        String sql = "INSERT INTO tickets(firstname, lastname, ticketfrom, ticketto, timebuy) VALUES (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());

            ps.setString(3, user.setTicketFrom(from));
            ps.setString(4, user.setTicketTo(to));
            ps.setString(5, date);

            ps.executeUpdate();
            System.out.println("Ticket successfully created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // register new user by user request and get default role "User"
    public void saveRegisterUser(User user) {
        String sql = "INSERT INTO users(firstname, lastname, user_password, user_role) VALUES (?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(db,username,password)){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            user.setRole("User");
            ps.setString(4, user.getRole());

            ps.executeUpdate();
            System.out.println("User registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // check ur input with DB and login if is true
    public User checkLoginUser(User user) {
        String sql = "SELECT * FROM users WHERE firstname=? AND lastname=? AND user_password=?";

        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User found = new User();
                found.setFirstName(rs.getString("firstname"));
                found.setLastName(rs.getString("lastname"));
                found.setPassword(rs.getString("user_password"));
                found.setRole(rs.getString("user_role"));
                return found;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


}
