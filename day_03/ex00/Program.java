package ex00;

public class Program {
    public static void main(String[] args){
        if (args.length != 1){
            if (args.length == 0)
                System.err.println("arguments required");
            else
                System.err.println("too many arguments");
            return;
        }
        int count;
        try {
            count = Integer.parseInt(args[0].substring(8));
        }
        catch (NumberFormatException ex){
            System.err.println("invalid argument");
            return;
        }
        Thread hen = new Thread(new HenDispute(count));
        Thread egg = new Thread(new EggDispute(count));
        hen.start();
        egg.start();
        try {
            hen.join();
            egg.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < count; i++){
            System.out.println("Human");
        }
    }
}