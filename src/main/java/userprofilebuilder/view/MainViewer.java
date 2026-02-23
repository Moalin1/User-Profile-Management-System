
package userprofilebuilder.view;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


/**
 * MainViewer is the *root* UI container for the application.
 *
 * In this module we build the whole UI as a hierarchy of JPanels.
 * The App class creates a JFrame and sets its content pane to an instance
 * of this panel.
 */

// Week 1 in W1R1 you will need to set the attributes of the frame in App.java
// Also to add an EditPanel in MainViewer for the components to go in.
public class MainViewer extends JPanel{
    public MainViewer() {
        
        setLayout (new BorderLayout());
        
        JPanel nameEditPanel = new JPanel();
        nameEditPanel.setBorder(new TitledBorder("Name"));
        
        nameEditPanel.setLayout(new  FlowLayout());         //  Setting up the buttons
        nameEditPanel.setLayout(new GridLayout(0,1));
        
        try (BufferedReader br = new BufferedReader(new FileReader("username.csv")))
        {
          String line;
          while ((line = br.readLine()) != null)
                  {
                      nameEditPanel.add(createRow(line));
                  }
}
          
          catch (IOException e)
                  {
                  System.out.println("Error loading names: " + e.getMessage());
                  }
        
        
        JTabbedPane tabs = new JTabbedPane();
        

        
        tabs.addTab("User Name", nameEditPanel);        //  Creating new tabs
        
        
        add(tabs, BorderLayout.CENTER);         //  Edits the layout of all the tabs and content in it
    }
    private JPanel createRow(String name)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        row.add(new JRadioButton(name));
        row.add(new JButton("Edit"));
        row.add(new JButton ("Delete"));
        
        return row;
    }
}
