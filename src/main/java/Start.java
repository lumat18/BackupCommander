import com.saver.FileManager;
import com.saver.FileSaver;

import java.io.File;
import java.io.IOException;

public class Start {

    public static void main(String[] args) {
        System.out.println("Starting app...");

//        File sourceFile = new File("file.txt");
//        File destFile = new File("dest\\file.txt");
//        try {
//            FileSaver.copyFile(sourceFile, destFile);
//        }
//        catch(IOException e){
//            System.out.println(e);
//        }
        File repoPath = new File("C:\\Users\\User\\IdeaProjects\\BackupCommander\\.repo");
        try{
            FileManager.copyDir(repoPath);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
