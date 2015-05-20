package jpa.demo.ids;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;

@Entity
public class Vehicle
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

    @Override
    public String toString()
    {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", model='" + model + '\'' +
                '}';
    }
}
