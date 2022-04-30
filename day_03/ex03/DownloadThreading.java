package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadThreading implements Runnable {

    private int threadId;

    private FileUrlList currentDownload;

    private static int count = 0;

    private static int dCount = 0;

    public DownloadThreading() {
        this.threadId = ++count;
    }

    @Override
    public void run() {
        while (dCount < Program.godList.size()) {
            currentDownload = Program.godList.get(dCount++);
            System.out.printf("Thread-%d start download file number %d\n", threadId, currentDownload.getId());
            try {
                downloadUsingStream(currentDownload.getUrlLink(), "./" + currentDownload.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Thread-%d finish download file number %d\n", threadId, currentDownload.getId());
        }
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
