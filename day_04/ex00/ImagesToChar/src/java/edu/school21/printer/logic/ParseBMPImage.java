package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ParseBMPImage {

    private char black;

    private char white;

    private String path;

    public ParseBMPImage(char black, char white, String path) {
        this.black = black;
        this.white = white;
        this.path = path;
    }

    public String[] unparsedArray() throws IOException {
        BufferedImage bmpImage = ImageIO.read(new FileInputStream(path));
        char[][] unparsedChars = new char[bmpImage.getHeight()][bmpImage.getWidth()];
        String[] unpairedString = new String[bmpImage.getHeight()];

        for (int i = 0; i < bmpImage.getHeight(); i++) {
            for (int j = 0; j < bmpImage.getWidth(); j++) {
                int color = bmpImage.getRGB(j, i);
                if (color == Color.BLACK.getRGB()) {
                    unparsedChars[i][j] = black;
                }
                else {
                    unparsedChars[i][j] = white;
                }
            }
            unpairedString[i] = new String(unparsedChars[i]);
        }
        return unpairedString;
    }
}
