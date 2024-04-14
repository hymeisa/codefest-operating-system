package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUI {

    private JFrame frame;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton removeButton;
    private List<JFrame> openGUIs; // List to store references to open GUIs
    private DesktopGUI desktopGUI; // Reference to the DesktopGUI instance

    public TaskManagerGUI(DesktopGUI desktopGUI) {
        this.desktopGUI = desktopGUI; // Store the reference to DesktopGUI
        frame = new JFrame("Task Manager");
        // Prevent closing the application when the frame is closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        openGUIs = new ArrayList<>(); // Initialize the list of open GUIs
    }

    private void addTask() {
        String task = JOptionPane.showInputDialog(frame, "Enter task:");
        if (task != null && !task.trim().isEmpty()) {
            listModel.addElement(task);
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to add a GUI to the list of open GUIs
    public void addOpenGUI(JFrame gui) {
        openGUIs.add(gui);
    }

    // Method to remove a GUI from the list of open GUIs
    public void removeOpenGUI(JFrame gui) {
        openGUIs.remove(gui);
    }

    // Method to show the TaskManagerGUI
    public void show() {
        frame.setVisible(true);
    }

    // Method to close the TaskManagerGUI
    public void close() {
        frame.dispose(); // Dispose the TaskManagerGUI frame
        // Check if there are no other open GUIs
        if (openGUIs.isEmpty()) {
            desktopGUI.showFrame(); // Show the DesktopGUI if no other GUIs are open
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DesktopGUI desktopGUI = new DesktopGUI();
            TaskManagerGUI taskManager = new TaskManagerGUI(desktopGUI); // Pass the reference to DesktopGUI
            desktopGUI.showFrame(); // Show the DesktopGUI
            taskManager.show(); // Show the TaskManagerGUI
        });
    }
}
