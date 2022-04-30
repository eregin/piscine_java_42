package ex02;

import java.util.*;

public class Program {

    public static int arraySize;

    public static int threadsCount;

    public static int[] arrayInt;

    public static int sum;

    private static List<Thread> ThreadLinkedList;

    public static void main(String[] args) {
        parse(args);
        int localSum = 0;
        arrayInt = randomArray(arraySize);
        for (int i = 0; i < arraySize; i++){
            localSum += arrayInt[i];
        }
        System.out.println("Sum: " + localSum);
        int from;
        int to = 0;
        for (int j = 0; j < threadsCount; j++) {
            from = to;
            to = (j + 1) * (arraySize/threadsCount + 1);
            ThreadLinkedList.add(new Thread(new SummatorThread(from , to)));
            ThreadLinkedList.get(j).start();
        }
        for (int j = 0; j < threadsCount; j++) {
            try {
                ThreadLinkedList.get(j).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads = " + sum);
    }

    public static void parse(String[] args){
        if (args.length != 2){
            System.err.println("arguments required");
            System.exit(1);
        }
        if (args[0].substring(0,12).equals("--arraySize=") && args[1].substring(0,15).equals("--threadsCount=")){
            try {
                arraySize = Integer.parseInt(args[0].substring(12));
                threadsCount = Integer.parseInt(args[1].substring(15));
                ThreadLinkedList = new ArrayList<>(threadsCount);
                if (arraySize > 2000000 || arraySize <= 0 || threadsCount <= 0 || threadsCount > arraySize){
                    System.err.println("invalid argument");
                    System.exit(1);
                }
            }
            catch (NumberFormatException ex){
                System.err.println("invalid argument");
                System.exit(1);
            }
        }
        else{
            System.err.println("invalid argument");
            System.exit(1);
        }
    }

    public static int[] randomArray(int arraySize) {
        int[] aInt = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            if (random.nextInt(2) == 1) {
                aInt[i] = random.nextInt(1000);
            }
            else {
                aInt[i] = (-1) * random.nextInt(1000);
            }
        }
        return aInt;
    }

}
