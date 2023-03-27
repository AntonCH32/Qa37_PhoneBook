package models;

public class User {
    private String email;
    private String password;

    public User setEmail(String Email)
    {
        this.email = Email;
        return this;
    }
    public User setPassword(String Password)
    {
        this.password = Password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{"
                + "Email='" + email + '\''
                + ", Password='" + password + '\'' + '}';

    }
}
