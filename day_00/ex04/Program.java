package ex04;

import java.util.Scanner;

public class Program {

    private static final int MAX_UNICODE = 65535;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] str_arr;
        int[][] statistic;
        int[] keep = new int[MAX_UNICODE];
        String  buf;
        char[]  arr;
        int i = 0;

        buf = sc.nextLine();
        if (buf.equals("")){
            sc.close();
            System.exit(0);
        }
        sc.close();
        arr = buf.toCharArray();
        while (i < buf.length()) {
            if (keep[(int)arr[i]] < 999) {
                keep[(int) arr[i]]++;
            }
            i++;
        }
        statistic = parse_int_arr(keep);
        str_arr = toStringArr(statistic);
        printStatistic(statistic, str_arr);
    }

    public static void printStatistic(int[][] int_arr, int[] info) {
        int i = 0;
        int j;
        int k;

        while (i < 10 && int_arr[i][0] != 0) {
            if (i < 9) {
                j = info[i] - info[i + 1];
            }
            else {
                j = 0;
            }
            if (j == 0) {
                if (int_arr[i][1] > 100) {
                    System.out.print(int_arr[i][1] + " ");
                }
                else if (int_arr[i][1] > 10) {
                    System.out.print(int_arr[i][1] + "  ");
                }
                else {
                    System.out.print(int_arr[i][1] + "   ");
                }
            }
            else {
                System.out.println(int_arr[i][1]);
                while (j > 0) {
                    k = 0;
                    while (k < i + 1) {
                        System.out.print("#   ");
                        k++;
                    }
                    if (j != 1) {
                        System.out.println();
                    }
                    j--;
                }
            }
            i++;
        }
        System.out.println();
        i = info[9];
        while (i > 0) {
            j = noZeroValues(info);
            while (j > 0) {
                System.out.print("#   ");
                j--;
            }
            System.out.println();
            i--;
        }
        while (i < 10 && int_arr[i][0] != 0) {
            System.out.print((char)int_arr[i][0] + "   ");
            i++;
        }
    }

    public static int noZeroValues(int[] arr){
        int i = 0;

        while (i < 10){
            if (arr[i] == -1) {
                break;
            }
            i++;
        }
        return i;
    }

    public static int[] toStringArr(int[][] int_arr) {
        int i = 0;
        int max = int_arr[0][1];
        int[] pre_res = new int[10];

        while(i < 10) {
            if (int_arr[i][1] == 0) {
                pre_res[i] = -1;
            }
            pre_res[i] = (10 * int_arr[i][1]) / max;
            i++;
        }
        return  pre_res;
    }

    public static int[][] parse_int_arr(int[] int_arr) {
        int[][] ret = new int[10][2];
        int i = 0;
        int j = 0;
        int max_val;
        int max_ind;

        while (j < 10) {
            max_val = 0;
            max_ind = 0;
            i = 0;
            while (i < MAX_UNICODE) {
                if (int_arr[i] > max_val) {
                    max_ind = i;
                    max_val = int_arr[i];
                }
                i++;
            }
            int_arr[max_ind] = -1;
            ret[j][0] = max_ind;
            ret[j][1] = max_val;
            j++;
        }
        return ret;
    }
}