package BussinessLayer;

public abstract class User
{
    private String userId;
    private String userName;
    private String email;
    private String passwordHash;
    private boolean role;

    public User(String userId, String userName, String email, String passwordHash, boolean role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
