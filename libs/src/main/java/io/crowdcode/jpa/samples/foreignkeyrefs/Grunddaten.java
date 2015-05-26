package io.crowdcode.jpa.samples.foreignkeyrefs;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grunddaten implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;

    private String vnr;

    public Long getId()
    {
        return id;
    }

    public String getVnr()
    {
        return vnr;
    }

    public Grunddaten withVnr(final String vnr)
    {
        this.vnr = vnr;
        return this;
    }

    @Override
    public String toString()
    {
        return "Grunddaten{" +
                "id=" + id +
                ", vnr='" + vnr + '\'' +
                '}';
    }
}
