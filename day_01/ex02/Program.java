package ex02;

public class Program {

    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();

        System.out.printf("number of users: %d\n", list.retrieveNumberOfUsers());
        list.addUser(new User("Tom", 50000));
        list.addUser(new User("Sam", 25000));
        list.addUser(new User("Paul", 98000));
        list.addUser(new User("Lee", 36000));
        list.addUser(new User("Tedd", 29000));
        list.addUser(new User("Lisa", 33000));
        System.out.printf("number of users: %d\n", list.retrieveNumberOfUsers());
        list.addUser(new User("Estela", 123000));
        list.addUser(new User("Akira", 110000));
        list.addUser(new User("Julian", 9000));
        list.addUser(new User("Chad", 150000));
        list.addUser(new User("Virgil", 2000));
        list.addUser(new User("Mike", 200));
        System.out.printf("number of users: %d\n", list.retrieveNumberOfUsers());
        try {
            System.out.println(list.retrieveUserById(6).getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

}
