package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageViewerGUI extends JFrame {
    private JLabel imageLabel;

    public ImageViewerGUI() {
        super("codefestOS Image Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        imageLabel = new JLabel();
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        Container contentPane = getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        });
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (isImageFile(selectedFile)) {
                try {
                    Image image = ImageIO.read(selectedFile);
                    ImageIcon icon = new ImageIcon(image);
                    imageLabel.setIcon(icon);
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error opening image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isImageFile(File file) {
        String name = file.getName();
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex == -1) {
            return false;
        }
        String extension = name.substring(dotIndex + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageViewerGUI().setVisible(true);
        });
    }
}
