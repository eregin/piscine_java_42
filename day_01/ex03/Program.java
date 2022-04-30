package ex03;

public class Program {

    public static void main(String[] args) {
        User a = new User("Lisa", 540);
        User b = new User("Roma", 270);
        User c = new User("Petya", 2000);

        Transaction one = new Transaction(a, b, 120);
        Transaction two = new Transaction(c, b, -40);
        Transaction three = new Transaction(b, a, 210);
        Transaction four = new Transaction(c, a, 330);
        Transaction five = new Transaction(c, b, 580);
        Transaction six = new Transaction(a, c, 3324235);
        Transaction[] arrA = a.getTransactionsList().transformIntoArray();
        Transaction[] arrB = b.getTransactionsList().transformIntoArray();
        Transaction[] arrC = c.getTransactionsList().transformIntoArray();
        System.out.printf("%s - balance: %d\n", a.getName(), a.getBalance());
        System.out.printf("%s - balance: %d\n", b.getName(), b.getBalance());
        System.out.printf("%s - balance: %d\n", c.getName(), c.getBalance());
        System.out.println("list transactions of a User");
        printTransactions(arrA);
        System.out.println("list transactions of b User");
        printTransactions(arrB);
        System.out.println("list transactions of c User");
        printTransactions(arrC);
        try {
            b.getTransactionsList().removeTransaction(arrB[1].getIdentifier());
        }
        catch (TransactionNotFoundException ex) {
            ex.printStackTrace();
        }
        arrB = b.getTransactionsList().transformIntoArray();
        System.out.println("ALTERED b User");
        printTransactions(arrB);
    }

    public static void printTransactions(Transaction[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("UUID: " + arr[i].getIdentifier() + ", ");
            System.out.printf("recipient: %s, sender: %s, amount: %d",
                    arr[i].getRecipient().getName(),
                    arr[i].getSender().getName(),
                    arr[i].getAmount());
            System.out.println();
        }
    }
}
