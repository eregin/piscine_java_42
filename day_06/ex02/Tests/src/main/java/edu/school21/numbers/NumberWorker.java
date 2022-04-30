package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number){
        int sqr_num;
        int del;
        boolean isPrime;

        sqr_num = ft_sqrt(number);
        if (number >= 2) {
            del = 2;
            isPrime = true;
            while (del < sqr_num + 1 ) {
                if (number % del++ == 0) {
                    if (number != 2) {
                        isPrime = false;
                    }
                    break;
                }
            }
        }
        else {
            throw  new IllegalNumberException();
        }
        return isPrime;
    }

    private int ft_sqrt(int num) {
        int i = 0;

        while (((long)i * (long)i) < (long)num) {
            i++;
        }
        return (i);
    }

    public int digitsSum(int number) {
        int sum = 0;

        while (number != 0)	{
            sum += number % 10;
            number /= 10;
        }
        return (sum);
    }

    class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {
            super("Number has to be >= 2");
        }
    }

}
