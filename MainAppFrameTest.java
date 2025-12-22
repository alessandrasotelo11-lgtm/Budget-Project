/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import java.util.Date;

public class MainAppFrameTest extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(MainAppFrameTest.class.getName());

    public MainAppFrameTest() {
        setTitle("Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new LoginPanel();
        PasswordTextField = new javax.swing.JPasswordField();
        UsernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        output = new javax.swing.JTextField();
        LoginLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(768, 768));

        usernameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username");

        passwordLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        // ✅ export-safe icon from resources (keeps same UI, works after export)
        LoginLabel.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/images/Login Button_.png")
        )); // NOI18N
        LoginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PasswordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(UsernameTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 225, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LoginLabel)
                        .addGap(232, 232, 232))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(484, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void LoginLabelMouseClicked(java.awt.event.MouseEvent evt) {
        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();
        Login account = new Login(username, password);

        //  keep your same style (reads/creates file in src/)
        File userFile = new File("src/" + username + ".txt");

        try {
            // -------- IF USER ALREADY EXISTS ----------
            if (userFile.exists()) {
                output.setText("User found. Checking password...");

                Scanner reader = new Scanner(userFile);
                String storedPassword = reader.nextLine();
                reader.close();

                if (account.confirmPassword(storedPassword)) {
                    output.setText("Login successful! Welcome, " + username);

                    // build login + budget (same logic)
                    Login login = new Login(username, password, userFile);
                    BudgetMainDashboard budget = new BudgetMainDashboard(new Date(0,0,0), 0, 0);

                    for (Transaction trans : TransactionHistoryFrame.getTransactions(userFile)) {
                        budget.addTransaction(trans);
                    }

                    //  Dashboard FIRST
                    DashboardFrame dash = new DashboardFrame(login, budget);
                    dash.setVisible(true);
                    this.dispose();

                } else {
                    output.setText("Incorrect password. Try again.");
                }

            } else {
                // -------- CREATE NEW USER ----------
                output.setText("User not found. Creating new account...");

                FileWriter writer = new FileWriter(userFile);
                writer.write(password + "\n\n");
                writer.close();

                System.out.println("Account created successfully!");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private TermDefinition createDefaultDefinitions() {
        TermDefinition d = new TermDefinition();
        d.setBudgetDefinition("Budget: plan to control income and expenses.");
        d.setSpendingDefinition("Spending: money that goes out (negative).");
        d.setEarningDefinition("Earning: money that comes in (positive).");
        d.setCategoryDefinition("Category: groups similar transactions.");
        d.setBalanceDefinition("Balance: current amount after transactions.");
        return d;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainAppFrameTest().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JTextField output;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel LoginLabel;
    // End of variables declaration
}

class LoginPanel extends javax.swing.JPanel {
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        java.net.URL url = getClass().getResource("/images/Login BG.png");
        if (url == null) {
            System.out.println("Image not found: /images/Login_BG.png");
            return;
        }

        java.awt.Image img = new javax.swing.ImageIcon(url).getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}