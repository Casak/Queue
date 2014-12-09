import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casak on 09.12.2014.
 */
public class UserFrame extends JFrame{
    public UserFrame(){

        setTitle("User Panel");
        setSize(240,300);

        toolkit= Toolkit.getDefaultToolkit();
        screenSize =  toolkit.getScreenSize();
        setLocation( (screenSize.width)/2, (int)((screenSize.height)/3));

        addAppoint = new JButton("Add New Appointment");
        addAppoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewAppointFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(false);
            }
        });


        reviewAppoint = new JButton("Review And Edit Appointments");
        reviewAppoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAppointFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(false);
            }
        });



        userButtonPanel = new JPanel();
        userInfoLabel = new JLabel("User Control Panel");

        userButtonPanel.add(userInfoLabel);
        userButtonPanel.add(addAppoint);
        userButtonPanel.add(reviewAppoint);


        setLayout(new GridLayout(2,0));
        add(userButtonPanel);



        setVisible(true);


    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new UserFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }


    private Toolkit toolkit;
    private Dimension screenSize;

    private JButton addAppoint;
    private JButton reviewAppoint;


    private JPanel userButtonPanel;
    private JLabel userInfoLabel;


}
