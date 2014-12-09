import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Casak on 26.11.2014.
 */
public class AuthFrame extends JFrame {

    public AuthFrame() {
        setTitle("Authentetication");
        setSize(240,105);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/2, (int)((screenSize.height)/3));

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://149.154.69.108/Queue","java","123456");
        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, "No Connection!");
        }
        catch(ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null, "Mysql Driver Not Found");
        }

        authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(3,2,5,5));


        loginLabel = new JLabel("Login");
        passLabel = new JLabel("Password");

        loginField = new JTextField(10);
        passField = new JPasswordField(10);

        processButton = new JButton("Authenticate");

        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);
        authPanel.add(new JLabel(""));
        authPanel.add(processButton);

        add(authPanel);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processResult = process(loginField.getText(),passField.getPassword());
                System.out.println(processResult);
                switch(processResult){
                    case 1: new AdminFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);; break;
                    case 2: new UserFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);; break;
                    case 0: ; break;
                    case -1: ; break;
                }
                setVisible(false);


            }
        });

    }
    private int process(String login, char[] password){
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT  `group` FROM `Users` WHERE  login='" + login + "' and password='" + String.valueOf(password) + "'");
            if(rs.next()) return rs.getInt("group");
        }
        catch(SQLException exp){
            JOptionPane.showMessageDialog(null, "Authentication problem");
            return -1;
        }

        return 0;
    }

    private Toolkit toolkit;
    private Dimension screenSize;

    private Connection con;
    private Statement stmt;
    private ResultSet rs;


    private JPanel authPanel;
    private JLabel loginLabel;
    private JLabel passLabel;
    private JTextField loginField;
    private JPasswordField passField;
    private JButton processButton;

    private int processResult;
}


