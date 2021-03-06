package classes;

abstract public class User {
    protected static int contor = 0;
    protected final int userID;
    protected String lastName;
    protected String firstName;
    protected String email;
    protected String phoneNumber;
    protected String nickName;
    protected String password;

    public User(int id, String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        if(id != -1){
            if(id >= contor)
                contor = id + 1;
            this.userID = id;
        }
        else{
            this.userID = contor;
            contor++;
        }
        this.nickName = nickName;
        this.password = password;
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
