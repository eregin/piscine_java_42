package ex00;

public class EggDispute extends Thread {

    private int count;

    public EggDispute(int count){
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++){
            System.out.println("Egg");
        }
    }
}