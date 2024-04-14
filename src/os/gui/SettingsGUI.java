package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

public class SettingsGUI extends JFrame {
    private JLabel wallpaperLabel;
    private DesktopGUI desktopGUI; // Reference to the DesktopGUI instance

    public SettingsGUI(DesktopGUI desktopGUI) {
        super("Settings");
        this.desktopGUI = desktopGUI; // Assign the reference
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton changeThemeButton = new JButton("Change Theme");
        JButton changeWallpaperButton = new JButton("Change Wallpaper");
        JButton applyButton = new JButton("Apply");

        panel.add(changeThemeButton);
        panel.add(changeWallpaperButton);
        panel.add(applyButton);

        wallpaperLabel = new JLabel();
        wallpaperLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(wallpaperLabel);

        changeThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement theme change functionality
                JOptionPane.showMessageDialog(SettingsGUI.this, "Theme change functionality not implemented yet.");
            }
        });

        changeWallpaperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeWallpaper();
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement apply settings functionality
                JOptionPane.showMessageDialog(SettingsGUI.this, "Settings applied.");
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void changeWallpaper() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(selectedFile);
                int width = image.getWidth(null);
                int height = image.getHeight(null);
                if (width == 800 && height == 600) {
                    // Call the setBackground method of DesktopGUI to change the wallpaper
                    desktopGUI.setBackground(image);
                    JOptionPane.showMessageDialog(this, "Wallpaper changed successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select an image with dimensions 800x600.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SettingsGUI(new DesktopGUI()).setVisible(true);
        });
    }
}
