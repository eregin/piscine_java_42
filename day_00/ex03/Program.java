package ex03;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        String buf;
        long number = 0;
        int i = 1;
        int j;
        int k = 0;
        long dec;
        boolean isWeek;
        boolean[] isWasIn = new boolean[18];
        Scanner sc = new Scanner(System.in);

        while (i < 19)
        {
            buf = sc.nextLine();
            isWeek = false;
            if (buf.equals("42")) {
                break;
            }
            if (buf.equals("")) {
                continue;
            }
            for (j = k; j < 19; j++) {
                if (buf.equals("Week " + j)) {
                    if (isWasIn[j - 1]) {
                        break;
                    }
                    isWasIn[j - 1] = true;
                    isWeek = true;
                    break;
                }
            }
            k = j;
            if (isWeek) {
                dec = 1;
                while (j > 1)
                {
                    dec *= 10;
                    j--;
                }
                number += (long)find_min(sc) * dec;
            }
            else {
                sc.close();
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            i++;
        }
        sc.close();
        print_progress(number);
    }

    public static void print_progress(long prt)
    {
        int i = 1;
        int buf;

        while (prt > 0) {
            buf = (int)(prt % 10);
            prt /= 10;
            if (buf == 0) {
                i++;
                continue;
            }
            System.out.print("Week " + i++ + " ");
            while (buf > 0) {
                System.out.print("=");
                buf--;
            }
            System.out.print(">\n");
        }
    }

    public static int find_min(Scanner sc)
    {
        int i = 0;
        int min = 9;
        int number;

        while (i < 5)
        {
            if (!sc.hasNextInt()) {
                return 0;
            }
            number = sc.nextInt();
            if (number <= 0 || number > 9) {
                return 0;
            }
            if (number < min) {
                min = number;
            }
            i++;
        }
        return min;
    }
}