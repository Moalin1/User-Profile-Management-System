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
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Profile Builder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Root UI container for the app.
            frame.setContentPane(new MainViewer());
            // some of the basic attributes of the frame should be set here
            frame.setVisible(true);
        });
    }
    
}