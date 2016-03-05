package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class User extends BaseEntity {
    @Length(max = 30)
    private String name;

    @Length(max = 30)
    private String surname;

    @Email
    private String email;

    @Column(unique = true)
    @Length(max = 20)
    private String username;

    @Length(max = 20)
    private String password;

    private boolean isAdmin;

    private String uploadPath;

    public User() {}

    public User(String name, String surname, String email,String username, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getUploadPath(){
        return uploadPath;
    }

    public Boolean isAdmin(){
        return isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUploadPath(String uploadPath){
        this.uploadPath = uploadPath;
    }

    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}
