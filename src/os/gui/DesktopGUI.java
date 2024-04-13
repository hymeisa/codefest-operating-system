package os.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DesktopGUI {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem quitMenuItem;
    private JLabel backgroundLabel;
    private int startX, startY; // Variables to store initial mouse click coordinates
    private int endX, endY; // Variables to store final mouse click coordinates
    private boolean drawing; // Flag to indicate if the user is drawing

    public DesktopGUI() {
        frame = new JFrame("codefestOS");
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

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Set background image
        setBackground();

        // Add mouse listener for left-click events
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startX = e.getX();
                    startY = e.getY();
                    drawing = true;
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    showContextMenu(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    endX = e.getX();
                    endY = e.getY();
                    drawing = false;
                    drawRectangle(startX, startY, endX, endY);
                }
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    endX = e.getX();
                    endY = e.getY();
                    frame.repaint();
                }
            }
        });

        // Initialize startup panel
        JPanel startupPanel = createStartupPanel();
        frame.getContentPane().add(startupPanel); // Add the startup panel to the frame's content pane

        // Set frame visibility
        frame.setVisible(true);

        // Initialize other components
    }

    private JPanel createStartupPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3x3 grid layout with gaps of 10 pixels

        // Add icons for different applications
        panel.add(createIconButton("Calculator", "calculator_icon.png"));
        panel.add(createIconButton("Text Editor", "text_editor_icon.png"));
        panel.add(createIconButton("Browser", "browser_icon.png"));
        panel.add(createIconButton("Terminal", "terminal_icon.png"));
        panel.add(createIconButton("File Explorer", "file_explorer_icon.png"));
        panel.add(createIconButton("Settings", "settings_icon.png"));
        panel.add(createIconButton("Music Player", "music_player_icon.png"));
        panel.add(createIconButton("Image Viewer", "image_viewer_icon.png"));
        panel.add(createIconButton("Shutdown", "shutdown_icon.png"));

        return panel;
    }


    private JButton createIconButton(String name, String iconFilename) {
        JButton button = new JButton(name);
        button.setIcon(new ImageIcon("src/os/gui/icons/" + iconFilename)); // Assuming icons are stored in src/os/gui/icons/
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for launching the corresponding application
                JOptionPane.showMessageDialog(frame, "Launching " + name + "...");
            }
        });
        return button;
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

    private void drawRectangle(int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) frame.getContentPane().getGraphics();
        g2d.setColor(Color.BLUE);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        int upperLeftX = Math.min(x1, x2);
        int upperLeftY = Math.min(y1, y2);
        g2d.fillRect(upperLeftX, upperLeftY, width, height);
    }

    private void showContextMenu(int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        popupMenu.add(newItem);
        popupMenu.add(openItem);
        popupMenu.add(saveItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality for new item
            }
        });

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality for open item
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality for save item
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });

        popupMenu.show(frame, x, y);
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
        });
    }
}
