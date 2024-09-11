# Java Terminal Command Simulator

## Description

This Java application simulates a basic terminal environment. Users can execute several common terminal commands to interact with the file system. The commands implemented in this project include:

- `ls`: Lists the contents of the current working directory.
- `ls -r`: Lists the contents of a directory in reverse order.
- `echo`: Prints the arguments to the console.
- `pwd`: Displays the current working directory.
- `cd <directory>`: Changes the current working directory.
- `mkdir <directory>`: Creates a new directory.
- `rmdir <directory>`: Removes an empty directory.
- `cp <source> <destination>`: Copies a file from source to destination.
- `rm <file>`: Removes a file.
- `touch <file>`: Creates an empty file if it does not exist.
- `append <file>`: Appends data to a file.

## Usage

1. **Compile the Project:**

   Ensure you have Java Development Kit (JDK) installed. Compile the project using:
   ```bash
   javac cmd.java
