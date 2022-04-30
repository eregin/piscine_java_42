package ex03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {

    public static int threadsCount;

    public static List<FileUrlList> godList;

    public static final String urlList = "./ex03/files_urls.txt";

    public static void main(String[] args) {
        parseArgs(args);
        godList = parseUrls();
        for (int i = 0; i < threadsCount; i++) {
            new Thread(new DownloadThreading()).start();
        }
    }

    public static List<FileUrlList> parseUrls() {
        List<FileUrlList> uList = new ArrayList<>();
        String buf;
        String[] spt1;
        String[] spt2;
        int idToAdd = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(urlList))) {
            while ((buf = reader.readLine()) != null) {
                 spt1 = buf.split(" ");
                 if (spt1.length != 2) {
                     System.err.println("file of urls corrupted");
                     System.exit(1);
                 }
                 try {
                     idToAdd = Integer.parseInt(spt1[0]);
                 } catch (NumberFormatException e) {
                     System.out.println("file of urls corrupted");
                     System.exit(1);
                 }
                 spt2 = buf.split("/");
                 uList.add(new FileUrlList(idToAdd, spt1[1], spt2[spt2.length - 1]));
            }
            return uList;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void parseArgs(String[] args){
        if (args.length != 1){
            System.err.println("arguments required");
            System.exit(1);
        }
        if (args[0].substring(0,15).equals("--threadsCount=")){
            try {
                threadsCount = Integer.parseInt(args[0].substring(15));
                if (threadsCount <= 0){
                    System.err.println("invalid argument");
                    System.exit(1);
                }
            }
            catch (NumberFormatException ex){
                System.err.println("invalid argument");
                System.exit(1);
            }
        }
        else{
            System.err.println("invalid argument");
            System.exit(1);
        }
    }

}
