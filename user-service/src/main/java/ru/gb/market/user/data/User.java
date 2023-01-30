package ru.gb.market.user.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToOne(cascade = {CascadeType.PERSIST,
                        CascadeType.REMOVE})
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public User(final String username, final String password, final Customer customer, final Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.customer = customer;
        this.roles = roles;
    }

    public User(final String username, final String password, final Customer customer) {
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password, final Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }
}
