/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */
import java.util.Date;

public class Transaction {

    //Declare instance variables
    private Date date;
    private double amount;
    private String category;
    public Boolean isEarnings;

    //Create object of category selector to test if category variable is valid
    CategorySelector testcategory = new CategorySelector();

    public Transaction() {
        //0 argument constructor
        this.date = new Date(); //Will initialize to the real life date when the constructor was called
    }

    public Transaction(Date date, double amount, String category, Boolean isEarnings) {

        if (amount < 0) //Throw exception if the amount is negative
            throw new IllegalArgumentException("Amount must be positive");

        if (!testcategory.isValidCategory(category)) //Throw exception if the category is not a valid category
            throw new IllegalArgumentException("Invalid Category");

        this.amount = amount;
        this.category = category;
        this.date = date;
        this.isEarnings = isEarnings;
    }

    //Get and set methods for instance variables
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getisEarnings() {
        return isEarnings;
    }

    public void setisEarnings(Boolean isEarnings) {
        this.isEarnings = isEarnings;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", isEarnings=" + isEarnings +
                '}';
    }
}