/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budget.budgetproject;

/**
 *
 * @author ale
 */
import javax.swing.*;
import java.awt.*;


public class DashboardFrame extends JFrame {

    private final Login login;
    private final BudgetMainDashboard budget;

    private JLabel balanceLabel;
    private JLabel userLabel;
    private JLabel addLabel;
    private JLabel defLabel;
    private JLabel searchLabel;
    private JLabel historyLabel;

    public DashboardFrame(Login login, BudgetMainDashboard budget) {
        this.login = login;
        this.budget = budget;
        

        setTitle("Budget Dashboard");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        initComponents();
    }

    private void initComponents() {
        balanceLabel = new JLabel(String.format("Balance: $%.2f",budget.getBalance()));
        userLabel = new JLabel("User: " + login.getUsername());
        Icon addpic = new ImageIcon(getClass().getResource("/images/+ Button.png"));
Icon Defpic = new ImageIcon(getClass().getResource("/images/Definition Button.png"));
Icon Searchpic = new ImageIcon(getClass().getResource("/images/Green Search Button.png"));
Icon Historypic = new ImageIcon(getClass().getResource("/images/History.png"));
        addLabel  = new JLabel(addpic);
        defLabel = new JLabel(Defpic);
        searchLabel = new JLabel(Searchpic);
        historyLabel = new JLabel(Historypic);
        
        
        balanceLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        
        userLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        
        balanceLabel.setForeground(Color.WHITE);
    
        userLabel.setForeground(Color.WHITE);
        
        addLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                if (e.getButton() == e.BUTTON1) {
                    openTransactionDialog();
                }
            }
        });
        defLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                if (e.getButton() == e.BUTTON1) {
                    openDefinitions();
                }
            }
        });        
        searchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                if (e.getButton() == e.BUTTON1) {
                    openSearchCategories();
                }
            }
        });
        historyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getButton() == e.BUTTON1) {
                openTransactionHistory();
            }
        }
        });
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(userLabel);
        topPanel.add(balanceLabel);
        
        

        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(addLabel);
        buttonPanel.add(defLabel);
        buttonPanel.add(searchLabel);
        buttonPanel.add(historyLabel);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        topPanel.setBackground(Color.GRAY);
        buttonPanel.setBackground(Color.GRAY);
    }

    private void openTransactionDialog() {
        TransactionDialog dialog = new TransactionDialog(this,login);
        dialog.setVisible(true);

        Transaction t = dialog.getTransaction();
        if (t != null) {
            budget.addTransaction(t);
            balanceLabel.setText(String.format("Balance: $%.2f",budget.getBalance()));
        }
    }
    private void openSearchCategories() {
        SearchCategories search = new SearchCategories(login);
        search.setVisible(true);
    }
    
    private void openTransactionHistory() {
        TransactionHistoryFrame history = new TransactionHistoryFrame(login,this);
        history.setVisible(true);
    }
    private void openDefinitions() {
        DefinitionFrame defFrame = new DefinitionFrame();
        defFrame.setVisible(true);
    }
    
}