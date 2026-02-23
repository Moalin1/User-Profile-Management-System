package userprofilebuilder;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import userprofilebuilder.view.MainViewer;


/**
 *
 * @author 
 */
 public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Application bootstrap.
        // Keep JFrame setup here; keep the UI itself in MainViewer (a JPanel).
        // The boiler plate code means the UI runs on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            MainViewer frame = MainViewer.getInstance();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("User Profile Builder");
            // some of the basic attributes of the frame should be set here
            frame.setVisible(true);
        });
    }
    
}