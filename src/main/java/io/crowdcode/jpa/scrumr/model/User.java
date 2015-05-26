package io.crowdcode.jpa.scrumr.model;

import javax.persistence.*;

@Entity
@Table(name="account",
        uniqueConstraints = @UniqueConstraint(name="my", columnNames = {"name","email"})
)
@NamedQueries(
        value = {@NamedQuery(
                name=User.FIND_BY_EMAIL,
                query="SELECT u FROM User u WHERE u.email = :email"
        ),
        @NamedQuery(
                name=User.FIND_NOT_IN,
                query="SELECT u FROM User u WHERE u not in (:users)"
        )}
)

public class User extends AbstractEntity
{
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_NOT_IN   = "User.findNotIn";

    private String name;

    @Column(unique = true)
    private String email;

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public User withEmail(final String email)
    {
        this.email = email;
        return this;
    }

    public User withName(final String name)
    {
        this.name = name;
        return this;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
