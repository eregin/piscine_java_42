package ex01;

public class Program {

    public static int operations = 0;

    public static void main(String[] args){
        if (args.length != 1){
            if (args.length == 0)
                System.err.println("arguments required");
            else
                System.err.println("too many arguments");
            return;
        }
        try {
            if (args[0].substring(0,8).equals("--count="))
                operations = Integer.parseInt(args[0].substring(8));
            else
                System.err.println("invalid argument");
        }
        catch (NumberFormatException ex){
            System.err.println("invalid argument");
            return;
        }
        Thread hen = new Thread(new HenDispute());
        Thread egg = new Thread(new EggDispute());
        hen.start();
        egg.start();
    }
}