import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casak on 09.12.2014.
 */
public class UserFrame extends JFrame{
    public UserFrame(){

        setTitle("Admininistrator Panel");
        setSize(240,300);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/2, (int)((screenSize.height)/3));

        addUser = new JButton("Add New User");
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewUserFrame();
                setVisible(false);
            }
        });


        delUser = new JButton("Delete User");
        delUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteUserFrame();
                setVisible(false);
            }
        });

        editUser = new JButton("Edit User Information");
        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditUserFrame();
                setVisible(false);
            }
        });

        editSelf = new JButton("Edit Your Information");
        editSelf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditAdminFrame();
                setVisible(false);
            }
        });

        userButtonPanel = new JPanel();
        adminButtonPanel = new JPanel();
        userInfoLabel = new JLabel("User Control Panel");
        adminInfoLabel = new JLabel("Your Control Panel");

        userButtonPanel.add(userInfoLabel);
        userButtonPanel.add(addUser);
        userButtonPanel.add(editUser);
        userButtonPanel.add(delUser);

        adminButtonPanel.add(adminInfoLabel);
        adminButtonPanel.add(editSelf);

        setLayout(new GridLayout(2,0));
        add(userButtonPanel);
        add(adminButtonPanel);


        setVisible(true);


    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new AdminFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }


    private Toolkit toolkit;
    private Dimension screenSize;

    private JButton addUser;
    private JButton delUser;
    private JButton editUser;
    private JButton editSelf;

    private JPanel userButtonPanel;
    private JPanel adminButtonPanel;
    private JLabel userInfoLabel;
    private JLabel adminInfoLabel;

}
