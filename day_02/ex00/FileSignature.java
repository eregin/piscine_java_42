package ex00;

public class FileSignature {
    private static int max_len = 0;
    public String file_extension;
    public String  signature;

    public FileSignature(String file_extension, String signature) {
        this.file_extension = file_extension;
        this.signature = signature;
    }

    public static int getMax_len() {
        return max_len;
    }

    public static void setMax_len(int max_len) {
        FileSignature.max_len = max_len;
    }
}
