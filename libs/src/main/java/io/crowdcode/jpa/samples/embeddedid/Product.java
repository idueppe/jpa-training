package io.crowdcode.jpa.samples.embeddedid;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@BatchSize(size = 200)
public class Product implements Serializable
{

    @Id
    private Long serialId;

    private String modelName;

    @OneToMany(mappedBy = "vehicleId.serialId")
    private Set<Vehicle> vehicles = new HashSet<>();

    public Long getSerialId()
    {
        return serialId;
    }

    public String getModelName()
    {
        return modelName;
    }

    public Collection<Vehicle> getVehicles()
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

    public Product withVehicles(final Collection<Vehicle> vehicles)
    {
        this.vehicles.clear();
        this.vehicles.addAll(vehicles);
        return this;
    }


    @Override
    public String toString()
    {
        return "Product{" +
                "serialId=" + serialId +
                ", modelName='" + modelName + '\'' +
                ", vehicles=" + Arrays.toString(vehicles.toArray()) +
                '}';
    }
}
