//package ex01;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Program {
    public static void main(String[] args) {
        HashSet<String> v = vectorDictionary(args[0], args[1]);
        Vector<Integer> idA = vectorFrequencyOfOccurrence(args[0], v);
        Vector<Integer> idB = vectorFrequencyOfOccurrence(args[1], v);
        int numer = numerator(idA, idB, v.size());
        double denum = denomirator(idA, idB);
        if (numer == 0) {
            System.out.printf("Similarity = %d", 0);
        }
        else {
            System.out.printf("Similarity = %.2f", numer / denum);
        }
    }

    public static double denomirator(Vector<Integer> a, Vector<Integer> b){
        int sumcub1 = 0;
        int sumcub2 = 0;

        for (Integer i : a ){
            sumcub1 += pow(i, 2);
        }
        for (Integer i : b ){
            sumcub2 += pow(i, 2);
        }
        return (sqrt(sumcub1)*sqrt(sumcub2));
    }

    public static int numerator(Vector<Integer> a, Vector<Integer> b, int size){
        int numer = 0;

        for (int i = 0; i < size; i++){
            numer += a.get(i) * b.get(i);
        }
        return numer;
    }

    public static Vector vectorFrequencyOfOccurrence(String filename, HashSet<String> dictionary){
        Vector<String> fStr = vectorOccurrence(filename);
        Vector<Integer> dStr = new Vector<>(dictionary.size());
        int count = 0;

        for (String a : dictionary){
            count = 0;
            for (String b : fStr) {
                if (a.equals(b)) {
                    count++;
                }
            }
            dStr.addElement(count);
        }
        return dStr;
    }

    public static HashSet vectorDictionary(String file1, String file2) {
        HashSet<String> vStr = new HashSet<String>();
        FileOutputStream fout;
        Vector<String> f1Str = vectorOccurrence(file1);
        Vector<String> f2Str = vectorOccurrence(file2);
        for (String a : f1Str){
            vStr.add(a);
        }
        for (String b : f2Str){
            vStr.add(b);
        }
        try {
            fout = new FileOutputStream("dictionary.txt", true);
            for (String hash: vStr) {
                fout.write(hash.getBytes());
                fout.write(" ".getBytes());
            }
            fout.write("\n".getBytes());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vStr;
    }

    public static Vector vectorOccurrence(String filename){
        Vector<String> vStr = new Vector<>(10, 2);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String buf;
            String line = "";
            String[] split;
            while ((buf = reader.readLine()) != null) {
                line += buf;
            }
            split = line.split(" ");
            for (String a: split) {
                vStr.addElement(a);
            }
            return vStr;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}

