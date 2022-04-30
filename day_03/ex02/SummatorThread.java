package ex02;

public class SummatorThread implements Runnable{

    private int partOfSum;

    private final int from;

    private final int to;

    private final int id;

    private static int count = 0;

    public SummatorThread(int from, int to) {
        this.from = from;
        if (to > Program.arraySize)
            this.to = Program.arraySize;
        else
            this.to = to;
        this.partOfSum = 0;
        this.id = count;
        count++;
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            partOfSum += Program.arrayInt[i];
        }
        synchronized (Program.class) {
            Program.sum += partOfSum;
            Program.class.notify();
        }
        System.out.printf("Thread %d: from %d to %d is %d\n", id, from, to - 1, partOfSum);
    }
}
