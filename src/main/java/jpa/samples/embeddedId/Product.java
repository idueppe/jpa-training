package jpa.samples.embeddedId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by idueppe on 20.05.15.
 */
@Entity
public class Product implements Serializable
{

    @Id
    private Long serialId;

    private String modelName;

    @OneToMany
    @JoinColumn(referencedColumnName = "serialid")
    private List<Vehicle> vehicles;

    public Long getSerialId()
    {
        return serialId;
    }

    public String getModelName()
    {
        return modelName;
    }

    public List<Vehicle> getVehicles()
    {
        return vehicles;
    }

    public Product withSerialId(final Long serialId)
    {
        this.serialId = serialId;
        return this;
    }

    public Product withModelName(final String modelName)
    {
        this.modelName = modelName;
        return this;
    }

    public Product withVehicles(final List<Vehicle> vehicles)
    {
        this.vehicles = vehicles;
        return this;
    }


    @Override
    public String toString()
    {
        return "Product{" +
                "serialId=" + serialId +
                ", modelName='" + modelName + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
