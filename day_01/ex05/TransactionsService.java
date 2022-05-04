import java.util.HashMap;
import java.util.UUID;

public class TransactionsService {

    private UsersList usersList;

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    public void addingUser(User usr){
        usersList.addUser(usr);
    }

    public Integer retrieveUserBalance(Integer id){
        try {
            return usersList.retrieveUserById(id).getBalance();
        }
        catch (UserNotFoundException ex00){
            System.out.println("Can't find user with id = " + id);
        }
        return -1;
    }

    public void performingTransferTransaction(Integer senderId, Integer recipientId, Integer amount)
            throws IllegalTransactionException {
        if (amount <= 0){
            throw new IllegalTransactionException("Negative transfer amount");
        }
        try {
            User sender = usersList.retrieveUserById(senderId);
            User recipient = usersList.retrieveUserById(recipientId);
            if (sender.getBalance() < amount){
                throw new IllegalTransactionException("Illegal Transaction Exception");
            }
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            UUID id_operate = UUID.randomUUID();
            Transaction credit = new Transaction(id_operate, recipient, sender, "CREDIT", amount);
            sender.getTransactionsList().addTransaction(credit);
            Transaction debit = new Transaction(id_operate, sender, recipient, "DEBIT", amount);
            recipient.getTransactionsList().addTransaction(debit);
        }
        catch (UserNotFoundException ex00) {
            ex00.printStackTrace();
        }
    }

    public Transaction[] getTransactionsOfUser(Integer usrId){
        try {
            User usr = usersList.retrieveUserById(usrId);
            return usr.getTransactionsList().transformIntoArray();
        }
        catch (UserNotFoundException ex00){
            ex00.printStackTrace();
        }
        return null;
    }

    public void removeTransactionOfUser(UUID transactionID, Integer userId){
        try {
            User usr = usersList.retrieveUserById(userId);
            try {
                usr.getTransactionsList().removeTransaction(transactionID);
            }
            catch (TransactionNotFoundException ex01){
                System.out.println("Transaction not found");
            }
        }
        catch (UserNotFoundException ex00){
            System.out.println("User not found");
        }
    }

    public Transaction[] getUnpairTransactions(){
        int k = 0;
        TransactionsList unpairedTransactions = new TransactionsLinkedList();
        for (int i = 0; i < usersList.retrieveNumberOfUsers(); i++) {
            User sender = usersList.retrieveUserByIndex(i);
            Transaction[] usrTransactionArray = sender.getTransactionsList().transformIntoArray();
            if (usrTransactionArray == null) {
                continue;
            }
            for (int j = 0; j < usrTransactionArray.length; j++){
                User recipient = usrTransactionArray[j].getRecipient();
                Transaction[] usrRecipientTransactionArray = recipient.getTransactionsList().transformIntoArray();
                if (usrRecipientTransactionArray == null){
                    unpairedTransactions.addTransaction(usrTransactionArray[j]);
                    continue;
                }
                for (k = 0; k < usrRecipientTransactionArray.length; k++){
                    if (usrRecipientTransactionArray[k].getIdentifier().equals(usrTransactionArray[j].getIdentifier())){
                        break;
                    }
                }
                if (k == usrRecipientTransactionArray.length) {
                    unpairedTransactions.addTransaction(usrTransactionArray[j]);
                }
            }
        }
        return unpairedTransactions.transformIntoArray();
    }
}
