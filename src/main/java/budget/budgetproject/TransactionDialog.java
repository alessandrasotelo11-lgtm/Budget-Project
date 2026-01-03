package budget.budgetproject;

/**
 *
 * @author ale
 */
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.io.FileWriter;
import java.util.Calendar;

public class TransactionDialog extends JDialog {

    private JTextField amountField;    
    private JComboBox<String> categoryCombo;
    private JComboBox<String> earningsCombo;
    private Login login;
    private Transaction transaction;

    public TransactionDialog(JFrame parent, Login login) {
        super(parent, "New Transaction", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        this.login = login;
        initComponents();
    }

    private void initComponents() {
        amountField = new JTextField(10);
        Icon createPic;
        Icon cancelPic;
        JLabel createLabel;
        JLabel cancelLabel;
        JLabel amountLabel;
        JLabel categoryLabel;
        JLabel earningsLabel;


        CategorySelector selector = new CategorySelector();
        categoryCombo = new JComboBox<>(selector.getAllCategories());
        earningsCombo = new JComboBox(new String[] {"Earnings","Spending"});
       createPic = new ImageIcon(getClass().getResource("/images/Create_Button.png"));
cancelPic = new ImageIcon(getClass().getResource("/images/Cancel_Button.png"));
        createLabel = new JLabel(createPic);
        cancelLabel = new JLabel(cancelPic);
        amountLabel = new JLabel("Amount:");
        categoryLabel = new JLabel("Category:");
        earningsLabel = new JLabel("Earnings/Spending:");
        
        
        createLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                if (e.getButton() == e.BUTTON1) {
                    saveTransaction();
                }
                   }
        });
        cancelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                
                 if (e.getButton() == e.BUTTON1) {
                    saveTransaction();
                }
            }
        });
        
        amountLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,25));
        categoryLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,25));   
        earningsLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,25));   
       

        amountLabel.setForeground(Color.WHITE);
        categoryLabel.setForeground(Color.WHITE);
        earningsLabel.setForeground(Color.WHITE);
  

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBackground(Color.GRAY);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(categoryLabel);
        panel.add(categoryCombo);
        panel.add(earningsLabel);
        panel.add(earningsCombo);
        panel.add(createLabel);
        panel.add(cancelLabel);

        add(panel);
    }
    private void earningsComboItemStateChanged(java.awt.event.ItemEvent evt) {
        
    }
   private void saveTransaction() {
        
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = (String) categoryCombo.getSelectedItem();
            Boolean isEarnings = earningsCombo.getSelectedIndex() == 0; //True if earnings was selected, false otherwise
            transaction = new Transaction(new Date(), amount, category, isEarnings);
            FileWriter write = new FileWriter(login.getData(),true);
            Formatter output = new Formatter(write);
            Calendar cal = Calendar.getInstance();
            //Output formatted data into text file
            output.format("Transaction,%d,%d,%d,Once,%s,Category0%d,%.2f\n",
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH)+1,
                    cal.get(Calendar.DAY_OF_MONTH),
                    (isEarnings) ? "Earning" : "Spending",
                    categoryCombo.getSelectedIndex()+1,amount);
            output.close();

            

             
            dispose();
        } catch (NumberFormatException ex) {
            //Disallow non-number values
            JOptionPane.showMessageDialog(this, "Amount must be a number.");
        }
        catch (IllegalArgumentException e) {
            //Disallow non-positive values
            JOptionPane.showMessageDialog(this, "Amount must be positive",
                    "Error",JOptionPane.ERROR_MESSAGE);                
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
                
    }
    
    public Transaction getTransaction() {
        return transaction;
    }

}