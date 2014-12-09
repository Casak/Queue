import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Casak on 09.12.2014.
 */
public class NewAppointFrame extends JFrame {
    public NewAppointFrame(){
        setTitle("User Panel");
        setSize(320,250);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/3, (int)((screenSize.height)/3));



        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://149.154.69.108/Queue", "java", "123456");

        }
        catch(SQLException exc){
            JOptionPane.showMessageDialog(null, "No Connection!");
        }
        catch(ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null, "Mysql Driver Not Found");
        }




        save = new JButton("Add New");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stmt = con.createStatement();
                    stmt.executeUpdate("INSERT INTO `Appointment`  VALUES (\"" + loginField.getText() + "\" ,  \"" + docNameField.getText() + "\",  \"" + docSurnameField.getText()
                            + "\",  \"" + idField.getText() + "\",  \"" + dateField.getText()  + "\")");
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
        buttons.setLayout(new GridLayout(1, 2));
        buttons.add(back);
        buttons.add(save);

        loginLabel = new JLabel("Login");
        docNameLabel = new JLabel("Doctor Name");
        docSurnameLabel = new JLabel("Doctor Surname");
        idLabel = new JLabel("Appointment ID");
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
        editor.add(idLabel);
        editor.add(idField);
        editor.add(dateLabel);
        editor.add(dateField);




        add(editor);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);





    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new NewAppointFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }



    private Toolkit toolkit;
    private Dimension screenSize;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    private JLabel loginLabel;
    private JLabel docNameLabel;
    private JLabel docSurnameLabel;
    private JLabel idLabel;
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
