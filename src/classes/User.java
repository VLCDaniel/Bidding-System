package classes;

abstract public class User {
    private static int contor = 0;
    private final int userID;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String nickName;
    private String password;

    public User(String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = contor;
        this.nickName = nickName;
        this.password = password;
        contor++;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "(" + userID + ") " + nickName + ", " + email + '\n';
    }
}
