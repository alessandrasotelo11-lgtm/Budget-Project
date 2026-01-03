/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */
public class CategorySelector {

    private static final String[] CATEGORIES = {
            "Food&Beverages",
            "Rentals",
            "Bills",
            "Gas",
            "Health",
            "Education",
            "Entertainment",
            "Shopping",
            "Other",
    };

    public String[] getAllCategories() {
        return CATEGORIES;
    }

    public boolean isValidCategory(String category) {
        for (String c : CATEGORIES) {
            if (c.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }    
}
