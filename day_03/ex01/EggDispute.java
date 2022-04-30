package ex01;

public class EggDispute extends Thread {

    @Override
    public void run() {
        synchronized (Program.class){
            while (Program.operations > 0){
                System.out.println("Egg");
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
