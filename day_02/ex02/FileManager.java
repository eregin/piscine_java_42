import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
    private String currentFolder;
    private String root;

    public FileManager() {
        this.root = System.getProperty("user.dir");
        this.currentFolder = root;
    }

    public FileManager(String root) {
        this.root = root;
        this.currentFolder = root;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }

    public void changeDirectory(String to) {
        if (to.equals("/")) {
            this.currentFolder = this.root;
        } else {
            this.currentFolder = alternateToken(to);
        }
        System.out.println(this.currentFolder);
    }

    private String alternateToken(String token) {
        String[] miniTokens = token.replace("\\", "/").split("/");
        String alternate = this.currentFolder;
        for (String mt: miniTokens) {
            if (mt.equals("..")) {
                int last = alternate.lastIndexOf("\\");
                alternate = alternate.substring(0, last);
            }
            else {
                alternate = alternate + "\\" + mt;
            }
        }
        return alternate;
    }

    public void listOfFiles() {
        File current = new File(currentFolder);
        File[] files = current.listFiles();
        if (files == null)
            return;
        for (File file: files) {
            long size = file.length();
            String sizeOf = "";
            if (size > 1024 * 1024) {
                sizeOf = (size/(1024 * 1024)) + " MB\n";
            } else if (size > 1024) {
                sizeOf = (size/(1024)) + " KB\n";
            } else {
                sizeOf = size + " bytes\n";
            }
            System.out.print(file.getName() + " " + sizeOf);
        }
        System.out.println();
    }

    public void moveFile(String from, String to) {
        File oldFile = new File(alternateToken(from));
        File newFile = new File(alternateToken(to));
        try {
            Files.copy(oldFile.toPath(), newFile.toPath());
            Files.delete(oldFile.toPath());
        } catch (IOException e) {
            System.out.println("Error in copying files");
        }
    }
}