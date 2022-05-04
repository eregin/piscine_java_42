import java.util.UUID;
public interface TransactionsList {

    void addTransaction(Transaction transaction);

    boolean removeTransaction(UUID id) throws TransactionNotFoundException;

    Transaction[] transformIntoArray();


}
