package org.example.repository;

import org.example.abstractions.User;
import java.sql.*;

public class OwnerRepository {
    private String db = "jdbc:postgresql://localhost:5432/Tickets";
    private String username = "postgres";
    private String password = "1234";

    public void adminMaker(User user) {
        String sql = "INSERT INTO users (user_role, firstname, lastname, user_password) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getRole());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();

            System.out.println("Admin created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOwner(User user) {
        if (checkRole("owner")) {
            System.out.println("Owner already exists!");
            return;
        }

        String sql = "INSERT INTO users (user_role, firstname, lastname, user_password) VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            PreparedStatement ps = connection.prepareStatement(sql);
            user.setRole("owner");
            ps.setString(1, user.getRole());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();

            System.out.println("Owner created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkRole(String role) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_role = ?";
        try (Connection connection = DriverManager.getConnection(db, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveFlight(User user) {
        String sql = "INSERT INTO flight(startfrom, headingto, status) VALUES (?,?,?)";
        try (Connection connection = DriverManager.getConnection(db, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getTicketFrom());
            ps.setString(2, user.getTicketTo());
            ps.setString(3, user.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearFlight(int flightId) {
        String sql = "DELETE FROM flight WHERE flight_id = ?";
        try (Connection connection = DriverManager.getConnection(db, username, password);
             PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setInt(1, flightId);
            int rows = pr.executeUpdate();
            if (rows > 0) {
                System.out.println("Flight deleted.");
            } else {
                System.out.println("No found flight with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsersTicket(int id) {
        String sql = "DELETE FROM tickets WHERE ticket_id = ?";
        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Ticket deleted.");
            } else {
                System.out.println("No found ticket with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
