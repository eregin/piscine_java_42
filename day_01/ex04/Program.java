package ex04;

public class Program {

    public static void main(String[] args) {
        User john = new User("john", 10);
        User joe = new User("joe", 100);
        User hoe = new User("Hoe", 1000);
        TransactionsService service = new TransactionsService();
        System.out.println("---Add user test---");
        service.addingUser(john);
        service.addingUser(joe);
        service.addingUser(hoe);
        System.out.println("---get users balance test---");
        System.out.print(john);
        System.out.println("---Balance is: " + service.retrieveUserBalance(john.getIdentifier()));
        System.out.print(joe);
        System.out.println("---Balance is: " + service.retrieveUserBalance(joe.getIdentifier()));
        System.out.print(hoe);
        System.out.println("---Balance is: " + service.retrieveUserBalance(hoe.getIdentifier()));
        System.out.println("---perform a transfer transaction test---");
        System.out.println("John balance(before transaction) is: " + service.retrieveUserBalance(john.getIdentifier()));
        System.out.println("Joe balance(before transaction) is: " + service.retrieveUserBalance(joe.getIdentifier()));
        try {
            service.performingTransferTransaction(john.getIdentifier(), joe.getIdentifier(), 10);
        } catch (IllegalTransactionException ex) {
            System.out.println("Error: IllegalTransactionException()");
        }
        System.out.println("John balance(after transaction) is: " + service.retrieveUserBalance(john.getIdentifier()));
        System.out.println("Joe balance(after transaction) is: " + service.retrieveUserBalance(joe.getIdentifier()));
        try {
            service.performingTransferTransaction(hoe.getIdentifier(), john.getIdentifier(), 230);
            service.performingTransferTransaction(hoe.getIdentifier(), joe.getIdentifier(), 25);
        } catch (IllegalTransactionException ex) {
            System.out.println("Error: IllegalTransactionException()");
        }
        System.out.println("John balance(after second transaction) is: " + service.retrieveUserBalance(john.getIdentifier()));
        System.out.println("Hoe balance(after second transaction) is: " + service.retrieveUserBalance(hoe.getIdentifier()));
        System.out.println("John's transactions:");
        printTransactions(service.getTransactionsOfUser(john.getIdentifier()));
        System.out.println("Joe's transactions:");
        printTransactions(service.getTransactionsOfUser(joe.getIdentifier()));
        System.out.println("Hoe's transactions:");
        printTransactions(hoe.getTransactionsList().transformIntoArray());
        System.out.println("_________________________________________");
        System.out.println("John's transactions (after deleting first transaction):");
        service.removeTransactionOfUser(john.getTransactionsList().transformIntoArray()[1].getIdentifier(), john.getIdentifier());
        printTransactions(service.getTransactionsOfUser(john.getIdentifier()));
        System.out.println("Hoe's transactions (after deleting first transaction):");
        service.removeTransactionOfUser(hoe.getTransactionsList().transformIntoArray()[1].getIdentifier(), hoe.getIdentifier());
        printTransactions(service.getTransactionsOfUser(john.getIdentifier()));
        System.out.println("_________________________________________");
        printTransactions(service.getUnpairTransactions());
        try {
            service.performingTransferTransaction(hoe.getIdentifier(), joe.getIdentifier(), 12000);
        } catch (IllegalTransactionException ex) {
            System.err.println("Error: IllegalTransactionException()");
        }
        printTransactions(hoe.getTransactionsList().transformIntoArray());
    }

    public static void printTransactions(Transaction[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print("UUID: " + arr[i].getIdentifier() + ", ");
            System.out.printf("recipient: %s, sender: %s, amount: %d, type: %s",
                    arr[i].getRecipient().getName(),
                    arr[i].getSender().getName(),
                    arr[i].getAmount(),
                    arr[i].getTransferCategoryToString());
            System.out.println();
        }
    }

}
