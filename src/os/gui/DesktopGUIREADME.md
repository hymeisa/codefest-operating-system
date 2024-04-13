Overview
The DesktopGUI class represents a basic desktop GUI for an operating system. It provides a window with a menu bar containing standard file and edit menus, along with the ability to set the frame size and handle quitting the application.

Usage
To use the DesktopGUI class:

Instantiate the DesktopGUI: Create an instance of the DesktopGUI class in your application.

java
Copy code
DesktopGUI gui = new DesktopGUI();
Display the DesktopGUI: Call the show() method to display the desktop GUI.

java
Copy code
gui.show();
Interact with the DesktopGUI: Use the menu items to perform actions such as cutting, copying, and pasting, and use the provided methods to set the frame size and handle quitting the application.

Components
Menu Bar: Contains standard file and edit menus with menu items.
File Menu: Contains menu items for file-related actions, such as quitting the application.
Edit Menu: Contains menu items for edit-related actions, such as cutting, copying, and pasting.
Quit MenuItem: Quits the application when selected from the file menu.
Functionality
Show Desktop GUI: Displays the desktop GUI window.
Set Frame Size: Allows setting the size of the desktop GUI window programmatically.
Quit Application: Closes the desktop GUI window and terminates the application when selected from the file menu.
Dependencies
Java Swing library
