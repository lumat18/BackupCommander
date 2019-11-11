package com.saver;

import com.Threads.CopyMessage;
import com.repository.Directories;
import com.repository.RepoManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Set;

public class FileManager {
    public static void fileSaver(String hashcode){
        String workingDirectory = Directories.getRepoDir();
        String srcDirectory = Directories.getWorkingDir();
        
        File srcFile = new File(srcDirectory);
        File dstFile = new File(workingDirectory + "/" + hashcode);

        try {
            copyDir(srcFile, dstFile, workingDirectory);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void copyDir(File srcFile, File dstFile, String srcDir) throws IOException {
        File repoDir = new File(srcDir);
        FileFilter repoFilter = pathname -> {
            if(pathname.equals(repoDir)){
                return false;
            }
            else {
                return true;
            }
        };
        CopyMessage copyMessage = new CopyMessage();
        copyMessage.start();
        FileUtils.copyDirectory(srcFile,dstFile,repoFilter);
        copyMessage.stop();
    }
    public static void clearDir(File currentVersion, String repoDir){
        for (File file: currentVersion.listFiles()) {
            if(file.getPath().equalsIgnoreCase(repoDir)) {
                //do nothing
            } else {
                //delete file
                file.delete();
            }
        }
    }
}
