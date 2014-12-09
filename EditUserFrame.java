import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

/**
 * Created by Casak on 04.12.2014.
 */
public class EditUserFrame extends JFrame {

    public EditUserFrame() {
        setTitle("Admininistrator Panel");
        setSize(320,250);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/3, (int)((screenSize.height)/3));



        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://149.154.69.108/Queue", "java", "123456");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM `Users` where `group` = \"2\"");
            rs.next();
            int length = rs.getInt(1);
            users = new String[length];
            rs.close();
            rs = stmt.executeQuery("SELECT `login` FROM `Users` where `group` = \"2\"");
            int i = 0;
            while(rs.next()){
                users[i] = rs.getString(1);
                i++;
            }
            stmt.close();
            rs.close();
        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, "No Connection!");
        }
        catch(ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null, "Mysql Driver Not Found");
        }

        if(users==null){
            users = new String[]{"user"};
        }
        userList = new JComboBox<String>(users);
        download = new JButton("Download User Info");
        download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT `login`, `password`, `group`, `name`, `surname`, `telephone`, `adress` FROM `Users` where `login` = \"" + userList.getSelectedItem() + "\"");
                    rs.next();
                        loginField.setText(rs.getString("login"));
                        passwordField.setText(rs.getString("password"));
                        groupField.setText(rs.getString("group"));
                        nameField.setText(rs.getString("name"));
                        surnameField.setText(rs.getString("surname"));
                        telephoneField.setText(rs.getString("telephone"));
                        adressField.setText(rs.getString("adress"));

                    stmt.close();
                    rs.close();
                }
                catch(SQLException exc){
                    JOptionPane.showMessageDialog(null, "No user!");
                }

            }
        });

        save = new JButton("Save User Info");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    stmt.executeUpdate("UPDATE `Users` SET `login` = \"" + loginField.getText() + "\" , `password` = \"" + passwordField.getText() + "\", `group` = \"" + groupField.getText()
                            + "\", `name` = \"" + nameField.getText() + "\", `surname` = \"" + surnameField.getText()  + "\", `telephone` = \"" + telephoneField.getText() + "\", `adress` = \"" + adressField.getText()
                            + "\" where `login` = \"" + loginField.getText() + "\"");
                    stmt.close();
                    JOptionPane.showMessageDialog(null, "Saved!");
                }
                catch(SQLException exc){
                    JOptionPane.showMessageDialog(null, "Error!");
                }

            }
        });

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFrame();
                setVisible(false);
            }
        });

        buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        buttons.add(back);
        buttons.add(download);
        buttons.add(save);

        loginLabel = new JLabel("Login");
        passwordLabel = new JLabel("Password");
        groupLabel = new JLabel("Group");
        nameLabel = new JLabel("Name");
        surnameLabel = new JLabel("Surname");
        telephoneLabel = new JLabel("Telephone");
        adressLabel = new JLabel("Adress");

        loginField = new JTextField(10);
        passwordField = new JTextField(10);
        groupField  = new JTextField(10);
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        telephoneField = new JTextField(10);
        adressField = new JTextField(10);

        editor = new JPanel(new GridLayout(7,2));
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



        add(editor);
        add(userList, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);





    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new EditUserFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }



    private Toolkit toolkit;
    private Dimension screenSize;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private JComboBox<String> userList;
    private String[] users;

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
    private JPanel buttons;

    private JButton back;
    private JButton download;
    private JButton save;





}
