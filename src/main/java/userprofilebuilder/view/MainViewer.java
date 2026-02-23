
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
import java.io.File;
import java.util.List;
import java.util.ArrayList;


/**
 * MainViewer is the *root* UI container for the application.
 *
 * In this module we build the whole UI as a hierarchy of JPanels.
 * The App class creates a JFrame and sets its content pane to an instance
 * of this panel.
 */

// Week 1 in W1R1 you will need to set the attributes of the frame in App.java
// Also to add an EditPanel in MainViewer for the components to go in.
public class MainViewer extends JFrame{
    
    private static MainViewer instance;
    private List<JRadioButton> allRadioButtons = new ArrayList<>(); 
    
    private MainViewer() {
        
        setLayout (new BorderLayout());
        
        JPanel nameEditPanel = new JPanel();
        nameEditPanel.setBorder(new TitledBorder("Name"));      // Creating the titles on the pages not the tabs
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new TitledBorder("Title"));
        
        JPanel emailPanel = new JPanel();
        emailPanel.setBorder(new TitledBorder("Email"));
        
        JMenuBar menuBar = new JMenuBar();      //  Making the File Menu Button
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem quitItem = new JMenuItem("Quit");
    
        quitItem.addActionListener(e ->         //  Quit action listener
        { 
            System.out.println("Quitting application...");
            System.exit(0);
                });
        
        
        openItem.addActionListener (e ->        //  Open action listener
        {
            System.out.println("Opening...");
        });
        
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menuBar.add(fileMenu);
        
        this.setJMenuBar(menuBar);
       
        
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        
        saveItem.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION){
                File fileToSave = fc.getSelectedFile();
                saveCSV(fileToSave.getPath());
            }
        });
        
        
        
                                                       
       nameEditPanel.setLayout(new GridLayout(0,1));        //  Layout for the buttons
        
        emailPanel.setLayout(new GridLayout(0,1));
        
        titlePanel.setLayout(new GridLayout(0,1));
        
        ButtonGroup nameGroup = new ButtonGroup();
        ButtonGroup titleGroup = new ButtonGroup();
        ButtonGroup emailGroup = new ButtonGroup();
        
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("userprofile.csv")))    //  Creating the try-catch method in order to avoid IOExceptions
        {
          String line;
          while ((line = br.readLine()) != null)
                  {
                      String[] data = line.split(",");
                      
                      
                      nameEditPanel.add(createRow(data[2], data[2], nameGroup));
                      emailPanel.add(createRow(data[3], data[2], emailGroup));
                      titlePanel.add(createRow(data[1], data[2], titleGroup));
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
    
    
    JPanel southPanel = new JPanel(new FlowLayout());
    
    JButton displayBtn = new JButton("Display Profile");
    displayBtn.addActionListener(e -> {
        String id = "";
        
        for (JRadioButton rb : allRadioButtons){
            if (rb.isSelected()){
                id = rb.getActionCommand();
                break;
            }
        }
        
        
        if(!id.isEmpty()){
            System.out.println("{");
            for (JRadioButton rb : allRadioButtons){
                if(rb.getActionCommand().equals(id) && !rb.getText().contains("@")
                   && !rb.getText().equals(id))   
                        {
                    System.out.println("Title: "+ rb.getText());
                }
                
            }
            System.out.println("Name: " + id);
            
            for (JRadioButton rb : allRadioButtons){
            if (rb.getActionCommand().equals(id) && rb.getText().contains("@")){
            System.out.println("Email: " + rb.getText());    
            }    
            }}
        
        System.out.println("}");
    });
    
    JButton addBtn = new JButton("Add Profile");
    addBtn.addActionListener (e -> {
        System.out.println("Adding profile...");
    });
    
    southPanel.add(displayBtn);
    southPanel.add(addBtn);
    this.add(southPanel, BorderLayout.SOUTH);
    
    
    }
    private JPanel createRow(String displayLabel, String name, ButtonGroup group)
    {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton radio = new JRadioButton(displayLabel);
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        
        group.add(radio);
        allRadioButtons.add(radio);
        
        radio.setActionCommand(name);
        
        radio.addActionListener(e -> {
            syncTabs(e.getActionCommand());
        });
        
        
        deleteBtn.addActionListener(e -> {
            
            Container parent = row.getParent();
            
            if (parent != null){
                
                parent.remove(row);
                parent.revalidate();
                parent.repaint();
                
                System.out.println("Deleted user: "+ name);
            }
        
            
            });
        
        
        
        
       editBtn.addActionListener(e -> {
           String newName = JOptionPane.showInputDialog("Enter new text:", radio.getText());        //  Essentially says when you click on the button the dialog box with this text comes up.
           if (newName != null && !newName.trim().isEmpty())         //  Checks if when it is trimmed it has not been trimmed from being "   "
           {
               radio.setText(newName);      //  If you input something in the dialog box it will change JRadioButton to whatever you input
               
               System.out.println("Information changed to: " +newName);
           }
           else
           {
               System.out.println("Please enter a valid input");
           }
       });
       
        
        row.add(radio);
        row.add(editBtn);
        row.add(deleteBtn);
        
        return row;
    }
    
    private void saveCSV(String p)
    {
        try(PrintWriter pw = new PrintWriter(new FileWriter(p)))
        {
            System.out.println("Data successfully saved to: " + p);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Error saving file: " +e.getMessage());
        }
    }
    
    
    public static MainViewer getInstance(){
        if (instance == null) {
            instance = new MainViewer();
        }
        return instance;
        }
    
    private void syncTabs(String selectedID) {
        for (JRadioButton rb : allRadioButtons)
        {
            if (rb.getActionCommand().equals(selectedID))
                    {
                        rb.setSelected(true);
                        
                    }
        }
    }
    }


