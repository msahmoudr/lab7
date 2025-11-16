package BussinessLayer;

public abstract class User
{
    private String userId;
    private String userName;
    private String email;
    private String passwordHash;
    private boolean role;

    public void setRole(boolean role) {
        this.role = role;
    }

    public User(String userId, String userName, String email, String passwordHash, boolean role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRole() {
        return role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
