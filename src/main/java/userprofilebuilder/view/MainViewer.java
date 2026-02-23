
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
        nameEditPanel.setBorder(new TitledBorder("Name"));      // Creating the titles on the pages not the tabs
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new TitledBorder("Title"));
        
        JPanel emailPanel = new JPanel();
        emailPanel.setBorder(new TitledBorder("Email"));
        
                                                       
       nameEditPanel.setLayout(new GridLayout(0,1));        //  Layout for the buttons
        
        emailPanel.setLayout(new GridLayout(0,1));
        
        titlePanel.setLayout(new GridLayout(0,1));
        
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("userprofile.csv")))    //  Creating the try-catch method in order to avoid IOExceptions
        {
          String line;
          while ((line = br.readLine()) != null)
                  {
                      String[] data = line.split(",");
                      
                      
                      nameEditPanel.add(createRow(data[2]));
                      emailPanel.add(createRow(data[3]));
                      titlePanel.add(createRow(data[1]));
                  }
}
          
          catch (IOException e)
                  {
                  System.out.println("Error loading names: " + e.getMessage());
                  }
        
        
        JTabbedPane tabs = new JTabbedPane();
        
        tabs.addTab("User Title", titlePanel);        //  Creating new tabs
        tabs.addTab("User Name", nameEditPanel);
        tabs.addTab("User Email", emailPanel);
        
        add(tabs, BorderLayout.CENTER);         //  Edits the layout of all the tabs and content in it
    }
    private JPanel createRow(String name)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton radio = new JRadioButton(name);
        JButton editBtn = new JButton("Edit");
        
       editBtn.addActionListener(e -> {
           System.out.println("Associated name to the console: " +radio.getText());
       });
        
        row.add(radio);
        row.add(editBtn);
        row.add(new JButton ("Delete"));
        
        return row;
    }
}
