package ex03;

public class User {

    private int identifier;

    private String name;

    private Integer balance;

    private TransactionsLinkedList transactionsList;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.transactionsList = new TransactionsLinkedList();
        this.name = name;
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance > 0)
            this.balance = balance;
    }
}
