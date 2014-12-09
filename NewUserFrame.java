import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Casak on 08.12.2014.
 */
public class NewUserFrame extends JFrame{
    public NewUserFrame(){


        setTitle("Admininistrator Panel");
        setSize(320, 250);

        toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width) / 3, (int) ((screenSize.height) / 3));


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://149.154.69.108/Queue", "java", "123456");


        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, "No Connection!");
        } catch (ClassNotFoundException exc) {
            JOptionPane.showMessageDialog(null, "Mysql Driver Not Found");
        }


        loginLabel = new JLabel("Login");
        passwordLabel = new JLabel("Password");
        groupLabel = new JLabel("Group");
        nameLabel = new JLabel("Name");
        surnameLabel = new JLabel("Surname");
        telephoneLabel = new JLabel("Telephone");
        adressLabel = new JLabel("Adress");

        loginField = new JTextField(10);
        passwordField = new JTextField(10);
        groupField = new JTextField(10);
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        telephoneField = new JTextField(10);
        adressField = new JTextField(10);

        editor = new JPanel(new GridLayout(7, 2));
        editor.add(loginLabel);
        editor.add(loginField);
        editor.add(passwordLabel);
        editor.add(passwordField);
        editor.add(groupLabel);
        editor.add(groupField);
        editor.add(nameLabel);
        editor.add(nameField);
        editor.add(surnameLabel);
        editor.add(surnameField);
        editor.add(telephoneLabel);
        editor.add(telephoneField);
        editor.add(adressLabel);
        editor.add(adressField);



        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new AdminFrame();
               setVisible(false);
            }
        });

        save = new JButton("Save User Info");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    stmt.executeUpdate("INSERT INTO `Users` VALUES (\"" + loginField.getText() + "\", \"" + passwordField.getText() + "\", \"" + groupField.getText()
                            + "\", \"" + nameField.getText() + "\", \"" + surnameField.getText() + "\", \"" + telephoneField.getText() + "\", \"" + adressField.getText()+ "\")");
                    stmt.close();
                    JOptionPane.showMessageDialog(null, "Saved!");
                } catch (SQLException exc) {

                    JOptionPane.showMessageDialog(null, "INSERT INTO `Users` VALUES (\"" + loginField.getText() + "\", \"" + passwordField.getText() + "\", \"" + groupField.getText()
                            + "\", \"" + nameField.getText() + "\", \"" + surnameField.getText() + "\", \"" + telephoneField.getText() + "\", \"" + adressField.getText()+ "\")");
                }

            }
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        buttons.add(back);
        buttons.add(save);




        add(editor);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewUserFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }


    private Toolkit toolkit;
    private Dimension screenSize;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel groupLabel;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel telephoneLabel;
    private JLabel adressLabel;

    private JTextField loginField;
    private JTextField passwordField;
    private JTextField groupField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField telephoneField;
    private JTextField adressField;

    private JPanel editor;

    private JButton back;
    private JButton save;

}
