package jpa.demo.ids;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class VehicleId implements Serializable
{

    private Long serialId;

    private String plateNumber;

    public VehicleId()
    {
    }

    public VehicleId(Long serialId, String plateNumber)
    {
//        this.serialId = serialId;
        this.plateNumber = plateNumber;
    }

    public Long getSerialId()
    {
        return serialId;
    }

    public void setSerialId(Long serialId)
    {
        this.serialId = serialId;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleId vehicleId = (VehicleId) o;

        if (serialId != null ? !serialId.equals(vehicleId.serialId) : vehicleId.serialId != null) return false;
        return !(plateNumber != null ? !plateNumber.equals(vehicleId.plateNumber) : vehicleId.plateNumber != null);

    }

    @Override
    public int hashCode()
    {
        int result = serialId != null ? serialId.hashCode() : 0;
        result = 31 * result + (plateNumber != null ? plateNumber.hashCode() : 0);
        return result;
    }
}
