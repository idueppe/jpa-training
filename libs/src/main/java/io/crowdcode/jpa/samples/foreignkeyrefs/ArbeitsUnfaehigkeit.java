package io.crowdcode.jpa.samples.foreignkeyrefs;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ArbeitsUnfaehigkeit implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @ManyToOne
    @JoinColumn(name = "vnr", referencedColumnName = "vnr")
    private Grunddaten grunddaten;

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Grunddaten getGrunddaten()
    {
        return grunddaten;
    }

    public ArbeitsUnfaehigkeit withName(final String name)
    {
        this.name = name;
        return this;
    }

    public ArbeitsUnfaehigkeit withGrunddaten(final Grunddaten grunddaten)
    {
        this.grunddaten = grunddaten;
        return this;
    }

    @Override
    public String toString()
    {
        return "ArbeitsUnfaehigkeit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grunddaten=" + grunddaten +
                '}';
    }
}
