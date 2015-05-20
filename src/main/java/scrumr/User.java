package scrumr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="account")
public class User extends AbstractEntity
{

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
