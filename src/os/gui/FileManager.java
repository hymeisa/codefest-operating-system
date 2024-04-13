package src.os.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileManager {

    private JFrame frame;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private File currentDirectory;

    public FileManager() {
        frame = new JFrame("File Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(fileList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton renameButton = new JButton("Rename");
        JButton deleteButton = new JButton("Delete");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFile();
            }
        });

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameFile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFile();
            }
        });

        buttonPanel.add(createButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        fileList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedFile = fileList.getSelectedValue();
                    if (selectedFile != null) {
                        File file = new File(currentDirectory, selectedFile);
                        if (file.isDirectory()) {
                            listFiles(file);
                        }
                    }
                }
            }
        });

        listFiles(FileSystemView.getFileSystemView().getHomeDirectory());
    }

    private void listFiles(File directory) {
        currentDirectory = directory;
        listModel.clear();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                listModel.addElement(file.getName());
            }
        }
    }

    private void createFile() {
        String fileName = JOptionPane.showInputDialog(frame, "Enter file name:");
        if (fileName != null && !fileName.trim().isEmpty()) {
            File newFile = new File(currentDirectory, fileName);
            try {
                if (newFile.createNewFile()) {
                    listFiles(currentDirectory);
                } else {
                    JOptionPane.showMessageDialog(frame, "File already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void renameFile() {
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            String newName = JOptionPane.showInputDialog(frame, "Enter new name:");
            if (newName != null && !newName.trim().isEmpty()) {
                File file = new File(currentDirectory, selectedFile);
                File newFile = new File(currentDirectory, newName);
                if (file.renameTo(newFile)) {
                    listFiles(currentDirectory);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to rename file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteFile() {
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete " + selectedFile + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                File file = new File(currentDirectory, selectedFile);
                if (file.delete()) {
                    listFiles(currentDirectory);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void show() {
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileManager fileManager = new FileManager();
            fileManager.show();
        });
    }
}