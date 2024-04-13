package os.user;

import os.kernel.FileSystem;
import os.kernel.Process;
import os.kernel.Scheduler;

import java.util.Scanner;

public class Shell {
    
    private FileSystem fileSystem;
    private Scheduler scheduler;
    // Add other necessary components
    
    public Shell() {
        // Initialize necessary components
        fileSystem = new FileSystem();
        scheduler = new Scheduler();
        // Initialize other components
    }
    
    public void run() {
        System.out.println("Welcome to your operating system!");
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("$ "); // Prompt for user input
            String input = scanner.nextLine().trim(); // Read user input
            
            // Parse and execute command
            executeCommand(input);
        }
    }
    
    private void executeCommand(String input) {
        // Split input into command and arguments
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        
        // Execute command based on user input
        switch (command) {
            case "help":
                displayHelp();
                break;
            case "ls":
                listFiles();
                break;
            // Add more commands as needed
            default:
                System.out.println("Command not recognized. Type 'help' for a list of available commands.");
        }
    }
    
    private void displayHelp() {
        // Display list of available commands and their descriptions
        System.out.println("List of available commands:");
        System.out.println("help       - Display this help message");
        System.out.println("ls         - List files in the current directory");
        // Add descriptions for other commands
    }
    
    private void listFiles() {
        // List files in the current directory
        // Example: fileSystem.listFiles();
    }
    
    // Add methods for other commands
    
    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.run();
    }
}
