package os.gui;

import javax.swing.*;
import java.awt.*;
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
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");

        // Add menu items to edit menu
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // Add edit menu to menu bar
        menuBar.add(editMenu);

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

    // Getter method to retrieve the current frame size
    public Dimension getFrameSize() {
        return frame.getSize();
    }

    // Setter method to set the frame size
    public void setFrameSize(int width, int height) {
        frame.setSize(width, height);
    }

    // Add other methods for adding components to the GUI, handling events, etc.


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DesktopGUI gui = new DesktopGUI();
            gui.show();
        });
    }
}
