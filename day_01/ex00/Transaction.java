package ex00;

import java.util.UUID;

public class Transaction {

    private UUID identifier;

    private User recipient;

    private User sender;

    private AssetsType transferCategory;

    private Integer transferAmount;

    private enum AssetsType{
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, Integer amount) {
        this.recipient = recipient;
        this.sender = sender;
        this.identifier = UUID.randomUUID();
        if (amount > 0)
            this.transferCategory = AssetsType.DEBIT;
        else
            this.transferCategory = AssetsType.CREDIT;
        if (sender.getBalance() > amount) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
        }
        this.transferAmount = amount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public AssetsType getTransferStatus() {
        return transferCategory;
    }

    public long getAmount() {
        return transferAmount;
    }

    public void setAmount(Integer amount) {
        this.transferAmount = amount;
    }
}
