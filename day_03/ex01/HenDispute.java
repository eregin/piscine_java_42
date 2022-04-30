package ex01;

public class HenDispute implements Runnable {

    @Override
    public void run() {
        synchronized (Program.class){
            while (Program.operations > 0){
                System.out.println("Hen");
                Program.operations--;
                Program.class.notify();
                try {
                    Program.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Program.class.notify();
        }
    }
}
