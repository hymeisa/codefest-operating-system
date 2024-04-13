package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

}
