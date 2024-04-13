package os.user.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesktopGUI {
    
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem quitMenuItem;
    // Add other necessary components
    
    public DesktopGUI() {
        frame = new JFrame("Operating System Desktop");
        frame.setSize(800, 600); // Set the initial size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
        
        // Create menu bar and menu items
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        quitMenuItem = new JMenuItem("Quit");
        
        // Add action listener to quit menu item
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });
        
        // Add menu items to file menu
        fileMenu.add(quitMenuItem);
        
        // Add file menu to menu bar
        menuBar.add(fileMenu);
        
        // Set menu bar to frame
        frame.setJMenuBar(menuBar);
        
        // Initialize other components
    }
    
    public void show() {
        // Set up and display the desktop GUI
        frame.setVisible(true);
    }
    
    // Method to handle quitting the application
    private void quitApplication() {
        frame.dispose(); // Close the frame
        System.exit(0); // Terminate the application
    }
    
    // Add methods for adding components to the GUI, handling events, etc.
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DesktopGUI gui = new DesktopGUI();
            gui.show();
        });
    }
}
