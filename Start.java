import javax.swing.*;
import java.awt.*;

/**
 * Created by Casak on 26.11.2014.
 */
public class Start {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
        public void run(){
            JFrame authFrame = new AuthFrame();
            authFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            authFrame.setVisible(true);
        }
        });
    }
}
