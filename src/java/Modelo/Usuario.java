package Modelo;

public class Usuario {
    String username;
    String password;
    String firstName;
    String phoneNumber;
    String email;
    Rol rol;

    public Usuario(String username, String password, String firstName, String phoneNumber, String email, Rol rol) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rol = rol;
    }
    
      public Usuario(String username, String password, String firstName, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
