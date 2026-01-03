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

public class BudgetMainDashboard {
    private Date date;
    private double balance;
    private double wage;

    public BudgetMainDashboard() {
        this.date = new Date();
        this.balance = 0.0;
        this.wage = 0.0;
    }

    public BudgetMainDashboard(Date date, double balance, double wage) {
        this.date = date;
        this.balance = balance;
        this.wage = wage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    /**
     * Add a transaction to the budget.
     * Positive = earning, negative = spending.
     */
    public void addTransaction(Transaction t) {
        if (t != null) {
            if (t.getisEarnings())
                balance += t.getAmount();
            else
                balance += -1 * t.getAmount();
        }   
            
    }

    @Override
    public String toString() {
        return "BudgetMainDashboard{" +
                "date=" + date +
                ", balance=" + balance +
                ", wage=" + wage +
                '}';
    }
}
