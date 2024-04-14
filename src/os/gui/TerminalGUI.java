package src.os.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class TerminalGUI extends JFrame implements Runnable {
    private JTextArea outputArea;
    private JTextField inputField;
    private JScrollPane scrollPane;
    private File currentDirectory;

    public TerminalGUI() {
        super("Terminal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        scrollPane = new JScrollPane(outputArea);
        inputField = new JTextField();

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processCommand(inputField.getText());
                inputField.setText("");
            }
        });

        // Set default directory
        currentDirectory = new File(System.getProperty("user.home"));

        setVisible(true);
    }

    private void processCommand(String command) {
        if (command.trim().isEmpty()) {
            return;
        }

        String[] tokens = command.split("\\s+");
        String cmd = tokens[0];
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

        switch (cmd) {
            case "cd":
                changeDirectory(args);
                break;
            case "ls":
                listFiles();
                break;
            case "touch":
                createFile(args);
                break;
            case "mkdir":
                createDirectory(args);
                break;
            case "rm":
                deleteFile(args);
                break;
            case "rmdir":
                deleteDirectory(args);
                break;
            case "pwd":
                showCurrentDirectory();
                break;
            case "help":
                showHelp();
                break;
            case "pwn":
                outputArea.append("You've been pwned!\n");
                break;
            default:
                outputArea.append("Unknown command: " + command + "\n");
        }
    }

    private void changeDirectory(String[] args) {
        if (args.length == 0) {
            outputArea.append("Usage: cd <directory>\n");
            return;
        }

        String directoryName = args[0];
        File newDir = new File(currentDirectory, directoryName);
        if (!newDir.exists() || !newDir.isDirectory()) {
            outputArea.append("Directory not found: " + directoryName + "\n");
            return;
        }

        currentDirectory = newDir;
        outputArea.append("Current directory: " + currentDirectory.getAbsolutePath() + "\n");
    }

    private void listFiles() {
        File[] files = currentDirectory.listFiles();
        if (files == null || files.length == 0) {
            outputArea.append("No files in directory\n");
            return;
        }

        for (File file : files) {
            outputArea.append(file.getName() + "\n");
        }
    }

    private void createFile(String[] args) {
        if (args.length == 0) {
            outputArea.append("Usage: touch <filename>\n");
            return;
        }

        String fileName = args[0];
        File newFile = new File(currentDirectory, fileName);
        try {
            if (newFile.createNewFile()) {
                outputArea.append("File created: " + fileName + "\n");
            } else {
                outputArea.append("File already exists: " + fileName + "\n");
            }
        } catch (IOException e) {
            outputArea.append("Error creating file: " + e.getMessage() + "\n");
        }
    }

    private void createDirectory(String[] args) {
        if (args.length == 0) {
            outputArea.append("Usage: mkdir <directoryname>\n");
            return;
        }

        String directoryName = args[0];
        File newDir = new File(currentDirectory, directoryName);
        if (newDir.mkdir()) {
            outputArea.append("Directory created: " + directoryName + "\n");
        } else {
            outputArea.append("Error creating directory: " + directoryName + "\n");
        }
    }

    private void deleteFile(String[] args) {
        if (args.length == 0) {
            outputArea.append("Usage: rm <filename>\n");
            return;
        }

        String fileName = args[0];
        File fileToDelete = new File(currentDirectory, fileName);
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            if (fileToDelete.delete()) {
                outputArea.append("File deleted: " + fileName + "\n");
            } else {
                outputArea.append("Failed to delete file: " + fileName + "\n");
            }
        } else {
            outputArea.append("File not found: " + fileName + "\n");
        }
    }

    private void deleteDirectory(String[] args) {
        if (args.length == 0) {
            outputArea.append("Usage: rmdir <directoryname>\n");
            return;
        }

        String directoryName = args[0];
        File dirToDelete = new File(currentDirectory, directoryName);
        if (dirToDelete.exists() && dirToDelete.isDirectory()) {
            if (dirToDelete.delete()) {
                outputArea.append("Directory deleted: " + directoryName + "\n");
            } else {
                outputArea.append("Failed to delete directory: " + directoryName + "\n");
            }
        } else {
            outputArea.append("Directory not found: " + directoryName + "\n");
        }
    }

    private void showCurrentDirectory() {
        outputArea.append("Current directory: " + currentDirectory.getAbsolutePath() + "\n");
    }

    private void showHelp() {
        outputArea.append("Available commands:\n");
        outputArea.append("cd <directory>   : Change directory\n");
        outputArea.append("ls               : List files\n");
        outputArea.append("touch <filename> : Create file\n");
        outputArea.append("mkdir <dirname>  : Create directory\n");
        outputArea.append("rm <filename>    : Delete file\n");
        outputArea.append("rmdir <dirname>  : Delete directory\n");
        outputArea.append("pwd              : Show current directory\n");
        outputArea.append("help             : Show available commands\n");
        outputArea.append("pwn              : You've been pwned!\n");
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(TerminalGUI::new);
    }
}