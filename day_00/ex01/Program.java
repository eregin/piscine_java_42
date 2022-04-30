package ex01;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;
        int sqr_num;
        int del;
        boolean prime;

        number = sc.nextInt();
        sqr_num = ft_sqrt(number);
        if (number >= 2) {
            del = 2;
            prime = true;
            while (del < sqr_num + 1 ) {
                if (number % del++ == 0) {
                    if (number != 2) {
                        prime = false;
                    }
                    break;
                }
            }
            System.out.printf("%b %d\n", prime, del - 2);
        }
        else {
            sc.close();
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        sc.close();
    }

    public static int ft_sqrt(int num) {
        int i = 0;

        while (((long)i * (long)i) < (long)num) {
            i++;
        }
        return (i);
    }
}
