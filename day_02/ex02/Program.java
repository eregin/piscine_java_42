import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        FileManager fm;
        if (args.length == 0) {
            fm = new FileManager();
        } else if (args.length == 1) {
            if (!args[0].substring(0, 17).equals("--current-folder="))
                return;
            fm = new FileManager(args[0].substring(17));
        } else {
            return;
        }
        System.out.println(fm.getCurrentFolder());
        while (!input.equals(Commands.EXIT)) {
            input = sc.nextLine();
            String[] tokens = input.split(" ");
            if (tokens.length > 0) {
                switch (tokens[0]) {
                    case Commands.CHANGE_DIRECTORY:
                        fm.changeDirectory(tokens[1]);
                        break;
                    case Commands.MOVE_FILE:
                        fm.moveFile(tokens[1], tokens[2]);
                        break;
                    case Commands.LIST_OF_FILES:
                        fm.listOfFiles();
                        break;
                }
            }
        }
    }
}
