package edu.school21.printer.app;

import edu.school21.printer.logic.ParseBMPImage;

import java.io.IOException;

public class Program {

    public static void main(String[] args) {

        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1){
            System.err.println("invalid arguments");
            System.exit(1);
        }
        char black = args[0].charAt(0);
        char white = args[1].charAt(0);
        String path = args[2];

        ParseBMPImage parseImage = new ParseBMPImage(black, white, path);

        String[] imageToTerminal = null;
        try {
            imageToTerminal = parseImage.unparsedArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageToTerminal != null) {
            for (int i = 0; i < imageToTerminal.length; i++) {
                System.out.println(imageToTerminal[i]);
            }
        }
    }

}
