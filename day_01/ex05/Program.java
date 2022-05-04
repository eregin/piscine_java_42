import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        TransactionsService menu = new TransactionsService();
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true){
            printMenu();
            try {
                choice = sc.nextInt();
            }
            catch (NoSuchElementException ex){
                System.out.println("Incorrect input. Please try type number between 1 and 7");
                if (sc.hasNext())
                    sc.nextLine();
                continue;
            }
            if (choice == 7)
                break;
            switch (choice){
                case 1:
                    addingUser(sc, menu);
                    break;
                case 2:
                    viewUsersBalance(sc, menu);
                    break;
                case 3:
                    performTransfer(sc, menu);
                    break;
                case 4:
                    viewAllTransactions(sc, menu);
                    break;
                case 5:
                    removeTransfer(sc, menu);
                    break;
                case 6:
                    checkValidity(menu);
                    break;
                default:
                    System.out.println("Incorrect input. Please try type number between 1 and 7");
                    break;
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    public static void printMenu(){
        System.out.println("1. Add a user");
        System.out.println("2. View user balances ");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
    }

    public static void addingUser(Scanner sc, TransactionsService menu){
        String name;
        User usr;
        int balance;
        System.out.println("Enter a user name and a balance");
        try {
            name = sc.next();
            balance = sc.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("Error: can't add such User");
            return;
        }
        if (balance < 0 || !sc.nextLine().equals("")) {
            System.out.println("Error: can't add such User");
            return;
        }
        usr = new User(name, balance);
        menu.addingUser(usr);
        System.out.printf("User with id = %d is added\n", usr.getIdentifier());
    }

    public static void viewUsersBalance(Scanner sc,TransactionsService menu){
        int id;
        System.out.println("Enter user id");
        try {
            id = sc.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("Error: here you should type user id");
            return;
        }
        if (id < 0 || !sc.nextLine().equals("")) {
            System.out.println("Error: here you should type user id");
            return;
        }
        id = menu.retrieveUserBalance(id);
        if (id < 0){
            return;
        }
        System.out.println(id);
    }

    public static void performTransfer(Scanner sc, TransactionsService menu){
        int sender;
        int recipient;
        int amount;
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        try {
            sender = sc.nextInt();
            recipient = sc.nextInt();
            amount = sc.nextInt();
            menu.performingTransferTransaction(sender, recipient, amount);
        }
        catch (InputMismatchException | IllegalTransactionException ex) {
            System.out.println("Error: illegal transfer operation");
            return;
        }
        if (!sc.nextLine().equals("")) {
            System.out.println("Error: illegal transfer operation");
            return;
        }
        System.out.println("The transfer is completed");
    }

    public static void viewAllTransactions(Scanner sc, TransactionsService menu){
        int id;
        Transaction[] transactions;
        System.out.println("Enter user id");
        try {
            id = sc.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("Error: here you should type user id");
            return;
        }
        if (id < 0 || !sc.nextLine().equals("")) {
            System.out.println("Error: here you should type user id");
            return;
        }
        transactions = menu.getTransactionsOfUser(id);
        if (transactions == null || transactions.length == 0) {
            System.out.println("There is no transaction for chosen user");
            return;
        }
        printTransactions(transactions);
    }

    public static void removeTransfer(Scanner sc, TransactionsService menu) {
        int id;
        String uuid;
        System.out.println("Enter a user ID and a transfer ID");
        try {
            id = sc.nextInt();
            uuid = sc.next();
        }
        catch (InputMismatchException ex) {
            System.out.println("Error: here you should type user id and UUId of transaction");
            return;
        }
        if (id < 0 || !sc.nextLine().equals("")) {
            System.out.println("Error: here you should type user id and UUId of transaction");
            return;
        }
        menu.removeTransactionOfUser(UUID.fromString(uuid), id);
    }

    public static void checkValidity(TransactionsService menu) {
        Transaction[] transactions = menu.getUnpairTransactions();
        System.out.println("Check results :");
        if (transactions == null || transactions.length == 0){
            System.out.println("transactions are valid");
            return;
        }
        printInvalid(transactions);
    }

    public static void printTransactions(Transaction[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("To %s(id = %d) ",
                    arr[i].getRecipient().getName(),
                    arr[i].getRecipient().getIdentifier());
            if (arr[i].getTransferCategoryToString().equals("CREDIT")){
                System.out.print("-" + arr[i].getAmount());
            } else {
                System.out.print("+" + arr[i].getAmount());
            }
            System.out.println(" with id = " + arr[i].getIdentifier());
        }
    }

    public static void printInvalid(Transaction[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getTransferCategoryToString().equals("DEBIT")) {
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s" +
                                " from %s(id = %d) for %d", arr[i].getSender().getName(),
                        arr[i].getSender().getIdentifier(),
                        arr[i].getIdentifier().toString(),
                        arr[i].getRecipient().getName(),
                        arr[i].getRecipient().getIdentifier(),
                        arr[i].getAmount());
            } else {
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s" +
                                " from %s(id = %d) for %d", arr[i].getSender().getName(),
                        arr[i].getSender().getIdentifier(),
                        arr[i].getIdentifier().toString(),
                        arr[i].getRecipient().getName(),
                        arr[i].getRecipient().getIdentifier(),
                        arr[i].getAmount() * (-1));
            }
            System.out.println();
        }
    }
}
