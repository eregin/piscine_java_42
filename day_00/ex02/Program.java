package ex02;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;
        int count;

        count = 0;
        while ((number = sc.nextInt()) != 42) {
            if (is_prime(sum_of_digit(number))) {
                count++;
            }
        }
        System.out.printf("Count of coffee-request - %d\n", count);
        sc.close();
    }

    public static boolean is_prime(int number) {
        int sqr_num;
        int del;
        boolean prime;

        sqr_num = ft_sqrt(number);
        prime = true;
        if (number >= 2) {
            del = 2;
            while (del < sqr_num + 1 ) {
                if (number % del == 0) {
                    prime = false;
                    break;
                }
                del++;
            }
            return prime;
        }
        return false;
    }

    public static int sum_of_digit(int number) {
        int result;

        result = 0;
        while (number != 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public static int ft_sqrt(int num)
    {
        int i = 0;

        while (((long)i * (long)i) < (long)num) {
            i++;
        }
        return (i);
    }
}
