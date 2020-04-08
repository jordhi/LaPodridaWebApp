package cat.jhz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User  {
    private String id;

    private String email;
    private String password;
    private String fullName;

    List<Card> cartes;

    public User() {}

    public User(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Card> getCartes() {
        return cartes;
    }

    public void setCartes(List<Card> cartes) {
        this.cartes = cartes;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}