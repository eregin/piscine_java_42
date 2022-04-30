package ex02;

public class UsersArrayList implements UsersList {

    private final Integer DEFAULT_CAPACITY = 10;

    private Integer numberOfUsers;

    private Integer capacity;

    private User[] usersList;

    public Integer getCapacity() {
        return capacity;
    }

    public UsersArrayList() {
        numberOfUsers = 0;
        capacity = DEFAULT_CAPACITY;
        usersList = new User[capacity];
    }

    @Override
    public void addUser(User usr) {
        if (numberOfUsers.equals(capacity)){
            increaseCapacity();
        }
        usersList[numberOfUsers] = usr;
        numberOfUsers++;
    }

    private void increaseCapacity(){
        User[] tmp = new User[capacity * 2];
        for (int i = 0; i < numberOfUsers; i++){
            tmp[i] = usersList[i];
        }
        capacity *= 2;
        usersList = tmp;
    }

    @Override
    public User retrieveUserById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < numberOfUsers; i++){
            if (id.equals(usersList[i].getIdentifier()))
                return usersList[i];
        }
        throw new UserNotFoundException("User not found exception");
    }

    @Override
    public User retrieveUserByIndex(Integer index) {
        return usersList[index];
    }

    @Override
    public int retrieveNumberOfUsers() {
        return numberOfUsers;
    }
}
