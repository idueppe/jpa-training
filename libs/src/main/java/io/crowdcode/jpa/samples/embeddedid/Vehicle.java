package io.crowdcode.jpa.samples.embeddedid;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Vehicle implements Serializable
{

    @EmbeddedId
    private VehicleId vehicleId;

    private String model;

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public VehicleId getVehicleId()
    {
        return vehicleId;
    }

    public void setVehicleId(VehicleId vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Vehicle withVehicleId(final VehicleId vehicleId)
    {
        this.vehicleId = vehicleId;
        return this;
    }

    public Vehicle withModel(final String model)
    {
        this.model = model;
        return this;
    }

    @Override
    public String toString()
    {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", model='" + model + '\'' +
                '}';
    }
}
