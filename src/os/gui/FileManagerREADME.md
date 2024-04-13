FileManager.java
Overview
The FileManager class represents a basic file manager GUI component for navigating, creating, renaming, and deleting files and directories. It provides a list view of files and directories in the current directory, along with buttons for common file operations.

Usage
To use the FileManager class:

Instantiate the FileManager: Create an instance of the FileManager class in your application.

java
Copy code
FileManager fileManager = new FileManager();
Display the FileManager: Call the show() method to display the file manager GUI.

java
Copy code
fileManager.show();
Interact with the FileManager: Use the buttons and file list to navigate the file system, create new files, rename existing files, and delete files.

Components
File List: Displays the files and directories in the current directory.
Create Button: Creates a new file in the current directory.
Rename Button: Renames the selected file or directory.
Delete Button: Deletes the selected file or directory.
Functionality
List Files: Lists the files and directories in the current directory.
Create File: Prompts the user to enter a file name and creates a new file in the current directory.
Rename File: Renames the selected file or directory.
Delete File: Deletes the selected file or directory after confirmation.
Dependencies
Java Swing library