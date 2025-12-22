/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */
public class TermDefinition {
    private String budgetDefinition;
    private String spendingDefinition;
    private String earningDefinition;
    private String categoryDefinition;
    private String balanceDefinition;
  /**
     * Constructor to initialize all financial term definitions.
     */
    public TermDefinition() {
        this.budgetDefinition = "A detailed plan for how you will spend and save money over a set period.";
        this.spendingDefinition = "The act of paying money for goods, services, or settling debts.";
        this.earningDefinition = "Money received in exchange for work or through investments and interest.";
        this.categoryDefinition = "A grouping or label used to organize similar types of expenses or income.";
        this.balanceDefinition = "The amount of money remaining in an account after all debits and credits are calculated.";
    }

    // Getter and Setter for budgetDefinition
    public String getBudgetDefinition() {
        return budgetDefinition;
    }

    public void setBudgetDefinition(String b) {
        this.budgetDefinition = b;
    }

    // Getter and Setter for spendingDefinition
    public String getSpendingDefinition() {
        return spendingDefinition;
    }

    public void setSpendingDefinition(String s) {
        this.spendingDefinition = s;
    }

    // Getter and Setter for earningDefinition
    public String getEarningDefinition() {
        return earningDefinition;
    }

    public void setEarningDefinition(String e) {
        this.earningDefinition = e;
    }

    // Getter and Setter for categoryDefinition
    public String getCategoryDefinition() {
        return categoryDefinition;
    }

    public void setCategoryDefinition(String c) {
        this.categoryDefinition = c;
    }

    // Getter and Setter for balanceDefinition
    public String getBalanceDefinition() {
        return balanceDefinition;
    }

    public void setBalanceDefinition(String b) {
        this.balanceDefinition = b;
    }

    // toString method
    @Override
    public String toString() {
        return "--- Financial Term Definitions ---\n"
                + "Budget: " + getBudgetDefinition() + "\n"
                + "Spending: " + getSpendingDefinition() + "\n"
                + "Earning: " + getEarningDefinition() + "\n"
                + "Category: " + getCategoryDefinition() + "\n"
                + "Balance: " + getBalanceDefinition();
    }
}