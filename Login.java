/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */

import java.io.File;
public class Login {
    private String username;
    private String password;
    private File data;

    public Login() { }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Login(String username, String password, File data) {
        this(username,password);
        this.data = data;
    }

    // getters / setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        // auto-register idea could go here
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public File getData() {
        return data;
    }
    public void setData(File data) {
        this.data = data;
    }

    /**
     * Confirm if the password matches what we have stored.
     * I’m keeping the version with a parameter because in GUI you need to check user input.
     */
    public boolean confirmPassword(String passwordToCheck) {
        return this.password != null && this.password.equals(passwordToCheck);
    }

    // as in your spec
    public String retrieveData() {
        return "User: " + username + ", Password: " + password;
    }

    // deleteAccount() was extra – not implemented

    @Override
    public String toString() {
        return "Login{username='" + username + "'}";
    }
}
