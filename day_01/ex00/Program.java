package ex00;

public class Program {

    public static void main(String[] args) {

        User a = new User("Goga", 555);
        User b = new User("Lisa", 2180);

        Transaction c = new Transaction(a, b, 100);
        Transaction d = new Transaction(b, a, -200);
        Transaction f = new Transaction(a, b, 5000);

        System.out.printf("User: %s, balance: %d\n", a.getName(), a.getBalance());
        System.out.printf("User: %s, balance: %d\n", b.getName(), b.getBalance());
    }
}
