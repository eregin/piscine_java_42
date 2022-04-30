package ex04;

import java.util.UUID;

public class Transaction {

    private UUID identifier;

    private User recipient;

    private User sender;

    private AssetsType transferCategory;

    private Integer transferAmount;

    private enum AssetsType{
        DEBIT,
        CREDIT,
        INVALID
    }

    public Transaction(UUID identifier, User recipient, User sender, String transferCategory, Integer transferAmount) {
        this.identifier = identifier;
        this.recipient = recipient;
        this.sender = sender;
        if (transferCategory.equals("DEBIT"))
            this.transferCategory = AssetsType.DEBIT;
        else if (transferCategory.equals("CREDIT"))
            this.transferCategory = AssetsType.CREDIT;
        else
            this.transferCategory = AssetsType.INVALID;
        this.transferAmount = transferAmount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public String getTransferCategoryToString() {
        if (transferCategory == AssetsType.CREDIT)
            return "CREDIT";
        else if (transferCategory == AssetsType.DEBIT)
            return "DEBIT";
        else
            return "UNDEFINED";
    }

    public long getAmount() {
        return transferAmount;
    }
}
