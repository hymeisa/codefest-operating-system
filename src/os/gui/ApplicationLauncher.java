package os.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationLauncher {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton calculatorButton;
    private JButton textEditorButton;
    // Add more buttons for other applications

    public ApplicationLauncher() {
        frame = new JFrame("Application Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1)); // 2 rows for buttons
        
        calculatorButton = new JButton("Calculator");
        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchCalculator();
            }
        });
        mainPanel.add(calculatorButton);
        
        textEditorButton = new JButton("Text Editor");
        textEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchTextEditor();
            }
        });
        mainPanel.add(textEditorButton);
        
        // Add more buttons for other applications
        
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on screen
    }

    private void launchCalculator() {
        // Code to launch the calculator application
        JOptionPane.showMessageDialog(frame, "Launching Calculator");
    }

    private void launchTextEditor() {
        // Code to launch the text editor application
        JOptionPane.showMessageDialog(frame, "Launching Text Editor");
    }

    // Add methods for launching other applications
    
    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationLauncher launcher = new ApplicationLauncher();
            launcher.show();
        });
    }
}
