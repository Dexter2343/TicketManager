package org.example.abstractions;

public class User {
private String role;
private String firstName;
private String lastName;
private String ticketFrom;
private String ticketTo;
private String status;
private int flightId;
private String password;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTicketFrom() {
        return ticketFrom;
    }

    public String setTicketFrom(String ticketFrom) {
        this.ticketFrom = ticketFrom;
        return ticketFrom;
    }

    public String getTicketTo() {
        return ticketTo;
    }

    public String setTicketTo(String ticketTo) {
        this.ticketTo = ticketTo;
        return ticketTo;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
