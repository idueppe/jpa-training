package jpa.demo;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Embeddable
public class Address
{

    private String strasse;
    private String plz;
    private String ort;

    public Address()
    {
    }

    public Address(String strasse, String plz, String ort)
    {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }

    public String getStrasse()
    {
        return strasse;
    }

    public void setStrasse(String strasse)
    {
        this.strasse = strasse;
    }

    public String getPlz()
    {
        return plz;
    }

    public void setPlz(String plz)
    {
        this.plz = plz;
    }

    public String getOrt()
    {
        return ort;
    }

    public void setOrt(String ort)
    {
        this.ort = ort;
    }
}
