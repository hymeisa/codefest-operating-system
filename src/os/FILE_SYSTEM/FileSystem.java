package FILE_SYSTEM;
import java.io.*;

public class FileSystem {

    public void createFile(String filename){
        try {
            File file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created: + " + file.getName());
            }
            else{
                System.out.println("file already exists");
            }
        } catch(IOException e){
            System.out.println("an error has occurred");
            e.printStackTrace();
            
        }

    }

    public void deleteFile(String filename){
        File file = new File(filename);
        if(file.delete()){
            System.out.println("File deleted: " + file.getName());
        }
        else{
            System.out.println("failed to delete file.");
        }
    }

    public String readFile(String filename){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                content.append(line).append("\n");
            }
        } catch(IOException e){
            System.out.println("error occurred while reading file");
            e.printStackTrace();
        }
        return content.toString();
    }

    public void writeFile(String filename, String content){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write(content);
            System.out.println("Content written to file: " + filename);
        } catch(IOException e){
            System.out.println("error occurred while writing to file");
            e.printStackTrace();
        }

    }


    public void renameFile(String oldFileName, String newFilename){
        File oldFile = new File(oldFileName);
        File newFile = new File(newFilename);


    }

}
