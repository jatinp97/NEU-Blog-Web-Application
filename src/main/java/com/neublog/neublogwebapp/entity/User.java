package com.neublog.neublogwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(nullable = false,unique = true,name = "Email")
    private String email;
    @Column(nullable = false, name = "Password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Fetch-type =eager as when user will be retrieved from db, Hibernate will extract the ROLES
    //Cascade =ALL actions performed on User entity, it will be applied on Role entity

    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},//user_id is Foreign key of Users table
            inverseJoinColumns = {@JoinColumn(name = "name_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

}
