package com.example.BOO.Model;

import com.example.BOO.Enum.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "Admin", indexes = {
        @Index(name = "idx_admin_username", columnList = "username")
})
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @Column(unique = true)
    @NotBlank(message = "UserName can't be null")
    private String username ;

    @NotBlank(message = "Password can't be null")
    private String password;

    private Role role ;

    private String image ;

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
