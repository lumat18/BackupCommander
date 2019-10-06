import com.saver.FileManager;

import java.io.File;
import java.io.IOException;

public class Start {

    public static void main(String[] args) {
        System.out.println("Starting app...");

        File srcFile = new File("C:\\Users\\User\\IdeaProjects\\BackupCommander");
        File dstFile = new File("C:\\Users\\User\\IdeaProjects\\BackupCommander\\.repo");
        try{
            FileManager.copyDir(srcFile,dstFile);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
