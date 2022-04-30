package ex00;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        List<FileSignature> fileSignatures = parse();
        FileInputStream fin;
        FileOutputStream fout;
        String filename = "";
        Scanner sc = new Scanner(System.in);
        String buf;

        while (true) {
            int j = 0;
            buf = "";
            filename = sc.nextLine();
            if (filename.equals("42")) {
                sc.close();
                break;
            }
            try {
                fin = new FileInputStream(filename);
            }
            catch (java.io.FileNotFoundException ex){
                System.err.println("File not found");
                continue;
            }
            int i = -1;
            while ((i = fin.read()) != -1 && j < FileSignature.getMax_len()) {
                if (Integer.toHexString(i).length() == 1)
                    buf += "0";
                buf += Integer.toHexString(i).toUpperCase();
                j++;
                for (FileSignature a: fileSignatures) {
                    if (a.signature.equals(buf)) {
                        buf = a.file_extension;
                        j = FileSignature.getMax_len() + 1;
                        break;
                    }
                }
            }
            if (i == -1 || j == FileSignature.getMax_len()) {
                System.out.println("UNDEFINED");
                continue;
            }
            buf += "\n";
            fout = new FileOutputStream("./src/ex00/result.txt", true);
            fout.write(buf.getBytes());
            System.out.println("PROCEED");
            fout.close();
        }

    }

    public static List<FileSignature> parse() throws IOException {
        FileInputStream fin = new FileInputStream("./src/ex00/signature.txt");
        List<FileSignature> fileSignatures = new ArrayList<>();
        String buf = "";
        String temp = "";
        int i = -1;
        int count;
        while ((i = fin.read()) != -1){
            count = 0;
            if (i != 44) {
                buf += (char)i;
            }
            else {
                while ((i = fin.read()) != -1 && i != 10){
                    if (i != 32) {
                        count++;
                        temp += (char) i;
                    }
                }
                if (count / 2 > FileSignature.getMax_len())
                    FileSignature.setMax_len(count / 2);
                fileSignatures.add(new FileSignature(buf, temp));
                buf = "";
                temp = "";
            }
        }
        fin.close();
        return fileSignatures;
    }
}
