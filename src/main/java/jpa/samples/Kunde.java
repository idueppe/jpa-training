package jpa.samples;

import javax.persistence.*;

@Entity
@Table(name = "KundeA")
@SecondaryTable(name = "KundeB")
public class Kunde
{
    @Id
    private Long id;

    private String name;

    private String title;

    @Column(table = "KundeB", unique = true)
    private String email;

    @Embedded
    private Address address;

    @Version
    private Long version;

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Kunde{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", version=" + version +
                '}';
    }
}
