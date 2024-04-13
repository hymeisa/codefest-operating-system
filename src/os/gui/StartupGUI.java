package os.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartupGUI {

    private JFrame frame;

    public StartupGUI() {
        frame = new JFrame("codefestOS - Startup");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton startButton = new JButton("Start codefestOS");

        // Add action listener to the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the startup GUI window
                frame.dispose();
                // Launch the desktop GUI
                SwingUtilities.invokeLater(() -> {
                    new DesktopGUI().show();
                });
            }
        });

        panel.add(startButton);
        frame.add(panel, BorderLayout.CENTER);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        // Make the window visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StartupGUI();
        });
    }
}
