package jpa.scrumr.model;

import jpa.scrumr.model.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name="account",
        uniqueConstraints = @UniqueConstraint(name="my", columnNames = {"name","email"})
)
@NamedQueries(
        value = {@NamedQuery(
                name=User.FIND_BY_EMAIL,
                query="SELECT u FROM User u WHERE u.email = :email"
        )}
)

public class User extends AbstractEntity
{
    public final static String FIND_BY_EMAIL = "User.findByEmail";

    private String name;

    @Column(unique = true)
    private String email;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
