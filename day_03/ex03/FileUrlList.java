package ex03;

public class FileUrlList {
    private int      id;
    private String   urlLink;
    private String   fileName;

    public FileUrlList(int id, String urlLink, String fileName) {
        this.id = id;
        this.urlLink = urlLink;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public String getFileName() {
        return fileName;
    }
}
