import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Casak on 09.12.2014.
 */
public class ViewAppointFrame extends JFrame {

    public ViewAppointFrame(){
        setTitle("User Panel");
        setSize(320,250);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/3, (int)((screenSize.height)/3));



        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://149.154.69.108/Queue", "java", "123456");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM `Appointment` where `login` = \"user\"");
            rs.next();
            int length = rs.getInt(1);
            users = new String[length];
            rs.close();
            rs = stmt.executeQuery("SELECT `app_id` FROM `Appointment` where `login` = \"user\"");
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
        idList = new JComboBox<String>(users);
        download = new JButton("Download");
        download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("SELECT `login`, `doc_name`, `doc_surname`, `app_id`, `date` FROM `Appointment` where `app_id` = \"" + idList.getSelectedItem() + "\"");
                    rs.next();
                    System.out.println(idList.getSelectedItem());
                    loginField.setText(rs.getString("login"));
                    docNameField.setText(rs.getString("doc_name"));
                    docSurnameField.setText(rs.getString("doc_surname"));
                    idField.setText(rs.getString("app_id"));
                    dateField.setText(rs.getString("date"));


                    stmt.close();
                    rs.close();
                }
                catch(SQLException exc){
                    JOptionPane.showMessageDialog(null, "No user!");
                }

            }
        });

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    stmt.executeUpdate("UPDATE `Appointment` SET `login` = \"" + loginField.getText() + "\" , `doc_name` = \"" + docNameField.getText() + "\", `doc_surname` = \"" + docSurnameField.getText()
                            + "\", `app_id` = \"" + idField.getText() + "\", `date` = \"" + dateField.getText()  + "\" where `app_id` = \"" + idList.getSelectedItem() + "\"");
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
                new UserFrame();
                setVisible(false);
            }
        });

        buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        buttons.add(back);
        buttons.add(download);
        buttons.add(save);

        loginLabel = new JLabel("Login");
        docNameLabel = new JLabel("Doctor Name");
        docSurnameLabel = new JLabel("Doctor Surname");
        departmentLabel = new JLabel("Department");
        dateLabel = new JLabel("Date");


        loginField = new JTextField(10);
        docNameField = new JTextField(10);
        docSurnameField = new JTextField(10);
        idField = new JTextField(10);
        dateField = new JTextField(10);


        editor = new JPanel(new GridLayout(5,2));
        editor.add(loginLabel);
        editor.add(loginField);
        editor.add(docNameLabel);
        editor.add(docNameField);
        editor.add(docSurnameLabel);
        editor.add(docSurnameField);
        editor.add(departmentLabel);
        editor.add(idField);
        editor.add(dateLabel);
        editor.add(dateField);




        add(editor);
        add(idList, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);





    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new ViewAppointFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }



    private Toolkit toolkit;
    private Dimension screenSize;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private JComboBox<String> idList;
    private String[] users;

    private JLabel loginLabel;
    private JLabel docNameLabel;
    private JLabel docSurnameLabel;
    private JLabel departmentLabel;
    private JLabel dateLabel;


    private JTextField loginField;
    private JTextField docNameField;
    private JTextField docSurnameField;
    private JTextField idField;
    private JTextField dateField;

    private JPanel editor;
    private JPanel buttons;

    private JButton back;
    private JButton download;
    private JButton save;


}
