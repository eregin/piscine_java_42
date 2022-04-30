package ex00;

public class HenDispute implements Runnable {

    private int count;

    public HenDispute(int count){
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++){
            System.out.println("Hen");
        }
    }
}
