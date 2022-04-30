package ex01;

public class Program {

    public static void main(String[] args) {
        User a = new User("Paul", 50000);
        User b = new User("Elena", 35000);
        User c = new User("Felix", 2000);

        System.out.printf("id: %d, User: %s, balance: %d\n", a.getIdentifier(), a.getName(), a.getBalance());
        System.out.printf("id: %d, User: %s, balance: %d\n", b.getIdentifier(), b.getName(), b.getBalance());
        System.out.printf("id: %d, User: %s, balance: %d\n", c.getIdentifier(), c.getName(), c.getBalance());
    }

}
