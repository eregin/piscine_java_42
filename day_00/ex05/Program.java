package ex05;

import java.util.Scanner;

public class Program {

    static final int[][] sept = {
            {1, 8, 15, 22, 29},
            {2, 9, 16, 23, 30},
            {3, 10, 17, 24},
            {4, 11, 18, 25},
            {5, 12, 19, 26},
            {6, 13, 20, 27},
            {7, 14, 21, 28}
    };

    static boolean [][] timeOfDay = {
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
    };

    public static void main(String[] args) {
        String[] names = new String[10];
        char[][][] schedule = new char[10][30][6];

        parser(names, schedule);
        printScheduleHeader();
        printScheduleBody(names , schedule);
    }

    public static void printScheduleBody(String[] names, char[][][] schedule){
        int i = 0;
        int j;
        int k;

        while (i < 10){
            j = 0;
            if (names[i] != null) {
                System.out.printf("%10s", names[i]);
                while (j < 30) {
                    k = 0;
                    while (k < 6) {
                        if (timeOfDay[j % 7][k]) {
                            if (schedule[i][j][k] == 'T') {
                                System.out.printf("%10s|", " 1");
                            }
                            else if (schedule[i][j][k] == 'F') {
                                System.out.printf("%10s|", "-1");
                            }
                            else {
                                System.out.printf("%10s|", " ");
                            }
                        }
                        k++;
                    }
                    j++;
                }
                System.out.println("");
            }
            i++;
        }
    }

    public static void printScheduleHeader(){
        int k = 0;
        int i;
        int j;

        System.out.printf("%10s", " ");
        while (k < 31) {
            i = 0;
            while (i < 7) {
                j = 0;
                while (j < 6) {
                    if (timeOfDay[i][j] && isValidDay(k, i)) {
                        System.out.printf("%10s|", j + 1 + ":00 " + dayOfWeek(k));
                    }
                    j++;
                }
                i++;
            }
            k++;
        }
        System.out.println("");
    }

    public static void parser(String[] names, char[][][] schedule){
        Scanner sc = new Scanner(System.in);
        boolean isDelim = false;
        String buf;
        int i;
        int j;
        int k;

        for (i = 0; i < 10; i++) {
            buf = sc.next();
            if (buf.equals(".")) {
                isDelim = true;
                break;
            }
            if (buf.length() > 10) {
                sc.close();
                System.exit(1);
            }
            names[i] = buf;
        }
        if (!isDelim){
            sc.close();
            System.exit(1);
        }
        isDelim = false;
        for (int h = 0; h < 10; h++) {
            buf = sc.next();
            if (buf.equals(".")) {
                isDelim = true;
                break;
            }
            i = hourToInt(buf);
            buf = sc.next();
            j = dayIndex(buf);
            timeOfDay[j][i] = true;
            if (!sc.nextLine().equals("")) {
                break;
            }
        }
        if (!isDelim){
            sc.close();
            System.exit(1);
        }
        isDelim = false;
        for (int h = 0; h < 10; h++) {
            buf = sc.next();
            if (buf.equals(".")) {
                isDelim = true;
                break;
            }
            if (!sc.hasNextInt()) {
                sc.close();
                System.exit(1);
            }
            j = sc.nextInt();
            if (!sc.hasNextInt()){
                sc.close();
                System.exit(1);
            }
            k = sc.nextInt();
            i = nameOrder(names, buf);
            if (j > 9 || j < 0 || i < 0){
                sc.close();
                System.exit(1);
            }
            buf = sc.next();
            if (buf.equals("HERE")) {
                schedule[i][k - 1][j - 1] = 'T';
            }
            else if (buf.equals("NOT_HERE")) {
                schedule[i][k - 1][j - 1] = 'F';
            }
            else {
                System.exit(1);
            }
            sc.nextLine();
        }
        if (!isDelim){
            sc.close();
            System.exit(1);
        }
        sc.close();
    }

    public static int hourToInt(String num){
        switch (num) {
            case "1":
                return 0;
            case "2":
                return 1;
            case "3":
                return 2;
            case "4":
                return 3;
            case "5":
                return 4;
            case "6":
                return 5;
            default:
                System.exit(1);
        }
        return -1;
    }

    public static int dayIndex(String day)
    {
        switch (day){
            case "MO":
                return 6;
            case "TU":
                return 0;
            case "WE":
                return 1;
            case "TH":
                return 2;
            case "FR":
                return 3;
            case "SA":
                return 4;
            case "SU":
                return 5;
            default:
                return -1;
        }
    }

    public static int nameOrder(String[] arr, String name){
        for (int i = 0; i < 10; i++){
            if (name.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isValidDay(int day, int dayWeek){
        int i = 0;
        int j;
        boolean isVal = false;

        if (dayWeek < 2) {
            j = 5;
        }
        else {
            j = 4;
        }
        while (i < j) {
            if (sept[dayWeek][i] == day) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static String dayOfWeek(int day) {
        switch ((day - 1) % 7) {
            case 0:
                return ("TU " + day);
            case 1:
                return ("WE " + day);
            case 2:
                return ("TH " + day);
            case 3:
                return ("FR " + day);
            case 4:
                return ("SA " + day);
            case 5:
                return ("SU " + day);
            case 6:
                return ("MO " + day);
        }
        return "";
    }
}
