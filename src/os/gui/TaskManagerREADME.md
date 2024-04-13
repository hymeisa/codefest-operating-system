TaskManager.java
Overview
The TaskManager class represents a basic task manager GUI component for an operating system. It allows users to add and remove tasks from a list.

Usage
To use the TaskManager class:

Instantiate the TaskManager: Create an instance of the TaskManager class in your application.

java
Copy code
TaskManager taskManager = new TaskManager();
Display the TaskManager: Call the show() method to display the task manager GUI.

java
Copy code
taskManager.show();
Interact with the TaskManager: Click on the "Add Task" button to add a new task to the list. Select a task from the list and click on the "Remove Task" button to remove it.

Components
Task List: Displays the list of tasks.
Add Task Button: Adds a new task to the list when clicked.
Remove Task Button: Removes the selected task from the list when clicked.
Functionality
Add Task: Prompts the user to enter a task and adds it to the list.
Remove Task: Removes the selected task from the list.
Dependencies
Java Swing library
