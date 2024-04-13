package src.os.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DesktopGUI {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem quitMenuItem;
    private JLabel backgroundLabel;
    // Add other necessary components

    public DesktopGUI() {
        frame = new JFrame("Operating System Desktop");
        frame.setSize(800, 600); // Set the initial size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed

        // Initialize menu bar
        menuBar = new JMenuBar();

        // Create file menu and menu items
        fileMenu = new JMenu("File");
        quitMenuItem = new JMenuItem("Quit");

        // Add action listener to quit menu item
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });

        // Add quit menu item to file menu
        fileMenu.add(quitMenuItem);

        // Add file menu to menu bar
        menuBar.add(fileMenu);

        // Create edit menu and menu items
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

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Set background image
        setBackground();

        // Initialize other components
    }

    private void setBackground() {
        try {
            // Load the background image
            ImageIcon backgroundImage = new ImageIcon(ImageIO.read(new File("src/os/gui/circuit_board.jpg")));
            // Create a JLabel to hold the background image
            backgroundLabel = new JLabel(backgroundImage);
            // Set the size of the label to match the frame size
            backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            // Add the label to the frame's content pane
            frame.getContentPane().add(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
