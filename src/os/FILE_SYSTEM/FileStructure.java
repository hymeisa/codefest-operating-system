package FILE_SYSTEM;

import java.util.*;

// Represents a file or directory
abstract class FileSystemItem {
    protected String name;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public FileSystemItem(String name) {
        this.name = name;
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    // Method to be implemented by subclasses
    public abstract int getSize();
}

// Represents a directory which can contain files and sub-directories
class Directory extends FileSystemItem {
    private List<FileSystemItem> children;

    public Directory(String name) {
        super(name);
        children = new ArrayList<>();
    }

    public void addChild(FileSystemItem item) {
        children.add(item);
    }

    public List<FileSystemItem> getChildren() {
        return children;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (FileSystemItem item : children) {
            size += item.getSize();
        }
        return size;
    }
}

// Represents a file
class File extends FileSystemItem {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

// Main class to demonstrate the usage
public class FileStructure {
    public static void main(String[] args) {
        // Creating directories
        Directory root = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory pictures = new Directory("Pictures");

        // Creating files
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);
        File file3 = new File("image.jpg", 500);

        // Adding files to directories
        documents.addChild(file1);
        documents.addChild(file2);
        pictures.addChild(file3);

        // Adding directories to root
        root.addChild(documents);
        root.addChild(pictures);

        // Calculating total size of root directory
        int totalSize = root.getSize();
        System.out.println("Total size of root directory: " + totalSize + " bytes");
    }
}

