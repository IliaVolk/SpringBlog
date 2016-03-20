package com.springapp.entities;




import org.hibernate.validator.constraints.Email;
import sun.security.provider.ConfigFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "spitter")
public class Spitter implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    @Size(min = 4, max = 44, message = "Username must be between 4 and 44 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
    private String username;

    @Column(name = "password")
    @Size(min = 4, max = 44, message = "Password must be between 4 and 44 characters long.")
    private String password;

    @Column(name = "email")
    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "update_by_email")
    private Boolean updateByEmail;
    //TODO: fetch type must be declared as FetchType.LAZY when solution will by found
    //fetch = FetchType.EAGER
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spitter")
    private List<Spittle> spittles;

    public Spitter(){}
    public Spitter(String username, List<Spittle> spittles) {
        setUsername(username);
        setSpittles(spittles);
    }

    public List<Spittle> getSpittles() {
        return spittles;
    }

    public void setSpittles(List<Spittle> spittles) {
        this.spittles = spittles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getUpdateByEmail() {
        return updateByEmail;
    }

    public void setUpdateByEmail(Boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length()>44){
            username = username.substring(0,44);
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Spitter &&
                id != 0 &&
                id == ((Spitter) obj).getId();
    }

    @Override
    public int hashCode() {
        return (int) (super.hashCode() + id * 31 +
                (username == null ? 19:username.hashCode())*19);
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + username;
    }
}
