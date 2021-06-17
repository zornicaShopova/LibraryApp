package book.api.libplovediv;

public class User {
    String fullname;
    String email;
    String username;
    String pass;

    public User() {

    }

    public User(String fullname, String email, String pass) {
        this.fullname = fullname;
        this.email = email;
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
