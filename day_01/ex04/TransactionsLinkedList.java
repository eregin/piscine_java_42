package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private ListElement head;
    private ListElement tail;
    private Integer size;

    public TransactionsLinkedList() {
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        ListElement add = new ListElement();
        add.transaction = transaction;
        if (head == null){
            head = add;
            tail = add;
            add.next = null;
            add.prev = null;
        }
        else {
            tail.next = add;
            add.prev = tail;
            tail = add;
        }
        size++;
    }

    @Override
    public boolean removeTransaction(UUID id) throws TransactionNotFoundException {
        if (head == null)
            return false;
        if (head == tail){
            head = null;
            tail = null;
            size--;
            return true;
        }
        if (head.transaction.getIdentifier().equals(id)){
            head.next.prev = null;
            head = head.next;
            size--;
            return  true;
        }
        ListElement temp = head;
        while (temp.next != null) {
            if (temp.next.transaction.getIdentifier().equals(id)){
                if (tail == temp.next){
                    tail = temp;
                }
                else {
                    temp.next.next.prev = temp;
                    temp.next = temp.next.next;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    @Override
    public Transaction[] transformIntoArray() {
        if (size == 0)
            return null;
        Transaction[] array = new Transaction[size];
        ListElement temp = head;
        Integer i = 0;
        while (i != size)
        {
            array[i] = temp.transaction;
            temp = temp.next;
            i++;
        }
        return array;
    }

    class ListElement {
        ListElement next;
        ListElement prev;
        Transaction transaction;
    }

    public Integer getSize() {
        return size;
    }
}
