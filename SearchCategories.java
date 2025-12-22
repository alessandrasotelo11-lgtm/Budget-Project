/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */
import java.io.*;
import java.util.*;
import javax.swing.*;


public class SearchCategories extends javax.swing.JFrame { 
    private static final String APP_TITLE = "Category Search";
    private static final String CATEGORY_LABEL = "Select Category:";
    private static final String MONTH_LABEL = "Month:";
    private static final String[] MONTH_DESCRIPTIONS = {
        "01 - January", "02 - February", "03 - March", "04 - April", "05 - May", "06 - June",
        "07 - July", "08 - August", "09 - September", "10 - October", "11 - November", "12 - December"
    };
    private static final String YEAR_LABEL = "Year:";
    private static final String[] YEAR_OPTIONS = {
        "2025", "2026", "2027", "2028", "2029", "2030"
    };
    private List<String[]> allTransactions; 
    private Map<String, String> nameToCodeMap;
    private Login login;
    // *** Need to update to the logged in user name
    
    public static class CategorySummary {
        public int expenseCount = 0;
        public double expenseTotal = 0.0;
        public int incomeCount = 0;
        public double incomeTotal = 0.0;
 
    }
    
    // *** Need to update categories below or load variables ***
    
    public static Map<String, String> getDescriptions() {
        Map<String, String> categoryDescriptions = new HashMap<>();
        categoryDescriptions.put("Category01", "Food&Beverages");
        categoryDescriptions.put("Category02", "Rentals");
        categoryDescriptions.put("Category03", "Bills");
        categoryDescriptions.put("Category04", "Gas");
        categoryDescriptions.put("Category05", "Health");
        categoryDescriptions.put("Category06", "Education");
        categoryDescriptions.put("Category07", "Entertainment");
        categoryDescriptions.put("Category08", "Shopping");
        categoryDescriptions.put("Category09", "Others");
        return categoryDescriptions;
    }

    public static Map<String, CategorySummary> summarizeByCategory(List<String[]> data) {
        Map<String, CategorySummary> summaryMap = new HashMap<>();
        for (String[] parts : data) {
            if (parts.length < 8) continue;
            String type = parts[5].trim();      
            String categoryName = parts[6].trim(); 
            String amountStr = parts[7].trim(); 
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid amount: " + amountStr);
                continue;
            }

            CategorySummary summary = summaryMap.getOrDefault(categoryName, new CategorySummary());
            if (type.equalsIgnoreCase("Spending")) { 
                summary.expenseCount++;
                summary.expenseTotal += amount;
            } else if (type.equalsIgnoreCase("Earning")) { 
                summary.incomeCount++;
                summary.incomeTotal += amount;
            }
            summaryMap.put(categoryName, summary);
        }
        return summaryMap;
    }

    private int extractCategoryNumber(String category) {
        try {
            return Integer.parseInt(category.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public SearchCategories(Login login) {
        this.login = login;
        loadAllTransactions(); 
        initComponents();
        if (allTransactions == null || allTransactions.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Error loading budget data. Please check user file.", 
                "Data Error", 
                JOptionPane.ERROR_MESSAGE);
            searchButton.setEnabled(false);
        }
    }
    
    //private final String userFile = login.getData().getPath();
    private void loadAllTransactions() {
        allTransactions = new ArrayList<>();
        Map<String, String> categoryDescriptions = getDescriptions(); 
        nameToCodeMap = new HashMap<>();
        
        for (Map.Entry<String, String> entry : categoryDescriptions.entrySet()) {
            nameToCodeMap.put(entry.getValue(), entry.getKey());
        }

        String resourcePath = String.format("src/%s.txt",login.getUsername());
        try (InputStream is = new FileInputStream(resourcePath)) {
            if (is == null) return;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Transaction")) {
                        String[] parts = line.split(",");
                        if (parts.length >= 8) {
                            String originalCategoryCode = parts[6];
                            String descriptiveName = categoryDescriptions.getOrDefault(originalCategoryCode, originalCategoryCode);
                            parts[6] = descriptiveName; 
                            allTransactions.add(parts);
                        }
                    }
                }
            } 
        } catch (IOException e) {
            allTransactions = Collections.emptyList();
        } 
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        categoryCombo = new javax.swing.JComboBox<>();
        jLabelMonth = new javax.swing.JLabel();
        monthCombo = new javax.swing.JComboBox<>();
        jLabelYear = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(APP_TITLE);

        jLabel1.setText(CATEGORY_LABEL); 

        List<String> sortedCodes = new ArrayList<>(getDescriptions().keySet());
        sortedCodes.sort(Comparator.comparingInt(this::extractCategoryNumber));
        
        String[] descriptiveNamesArray = sortedCodes.stream()
                                            .map(code -> getDescriptions().get(code))
                                            .toArray(String[]::new);
        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(descriptiveNamesArray));
       
        jLabelMonth.setText(MONTH_LABEL);
        monthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(MONTH_DESCRIPTIONS));
        jLabelYear.setText(YEAR_LABEL);
        yearCombo.setModel(new javax.swing.DefaultComboBoxModel<>(YEAR_OPTIONS));
        searchButton.setText("Search");
        searchButton.addActionListener(evt -> searchButtonActionPerformed(evt));    
        exitButton.setText("Exit");
        exitButton.addActionListener(evt -> System.exit(0));
        resultArea.setEditable(false);
        resultArea.setColumns(20);
        resultArea.setRows(10);
        jScrollPane1.setViewportView(resultArea);
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMonth)
                    .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelYear)
                    .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED) 
                        .addComponent(exitButton))
                ).addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addComponent(jLabel1)
                .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelMonth)
                .addComponent(monthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelYear)
                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(exitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        setSize(325, 350); 
        setResizable(false); 
        setLocationRelativeTo(null);
    }
    
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        resultArea.setText("");
        String descriptiveNameSelected = categoryCombo.getSelectedItem().toString();
        String monthSelected = monthCombo.getSelectedItem().toString().substring(0, 2);
        String yearSelected = yearCombo.getSelectedItem().toString();

        List<String[]> filteredTransactions = new ArrayList<>();

        if (allTransactions == null || allTransactions.isEmpty()) {
             resultArea.setText("Error: Data not loaded.");
             return;
        }

        for (String[] parts : allTransactions) {
            String partsCategoryName = parts[6]; 
            
            if (parts.length >= 8) {
                String YY = parts[1];
                String MM = parts[2];

                if (partsCategoryName.equals(descriptiveNameSelected) 
                    && MM.equals(monthSelected)
                    && YY.equals(yearSelected)) {
                    filteredTransactions.add(Arrays.copyOf(parts, parts.length));
                }
            }
        } 

        if (filteredTransactions.isEmpty()) {
            resultArea.setText("No transactions found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Transactions (").append(descriptiveNameSelected).append(" - ").append(monthSelected).append("/").append(yearSelected).append("):\n");
        sb.append("--------------------------------------------------------------\n");
        
        for (String[] parts : filteredTransactions) {
            String date = parts[1] + "-" + parts[2] + "-" + parts[3]; 
            String catName = parts[6]; 
            String type = parts[5];
            
            try {
                double amount = Double.parseDouble(parts[7]);
                String sign = type.equalsIgnoreCase("Earning") ? "+$" : "- $"; 
                sb.append(String.format("%s  %s   %s%s\n", 
                    date, catName, sign, String.format("%.2f", amount)));
            } catch (NumberFormatException e) {
            }
        }
             
        Map<String, CategorySummary> summary =
                    summarizeByCategory(filteredTransactions);
        
        sb.append("==================================\n");
        sb.append("Monthly Summary\n");
        sb.append("==================================\n");
        
        for (String catName : summary.keySet()) { 
            CategorySummary s = summary.get(catName);
            double netTotal = s.incomeTotal - s.expenseTotal;
            String netTotalDisplay;
            
            if (netTotal < 0) {
                netTotalDisplay = String.format("- $%.2f", Math.abs(netTotal));
            } else {
                netTotalDisplay = String.format("$%.2f", netTotal);
            }
            
            sb.append(catName).append(":\n"); 
            sb.append("  Spending Total:  ").append(String.format("- $%.2f", s.expenseTotal)).append("\n");
            sb.append("  Earnings Total:     ").append(String.format("$%.2f", s.incomeTotal)).append("\n");
            sb.append("  ------------------------------------\n");
            sb.append("  Net Total:          ").append(netTotalDisplay).append("\n");
            sb.append("==================================\n");
        }            
        resultArea.setText(sb.toString());
    }
    
   /* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new SearchCategories(login).setVisible(true));
    } */

    private javax.swing.JComboBox<String> categoryCombo;
    private javax.swing.JComboBox<String> monthCombo;
    private javax.swing.JComboBox<String> yearCombo;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea resultArea;
}
