package ex04;

public interface UsersList {

    void addUser(User usr);

    User retrieveUserById(Integer id) throws UserNotFoundException;

    User retrieveUserByIndex(Integer index);

    int retrieveNumberOfUsers();

}
