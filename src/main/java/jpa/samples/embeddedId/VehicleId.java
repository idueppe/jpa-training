package jpa.samples.embeddedId;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VehicleId implements Serializable
{

    private Long serialId;

    private String plateNumber;

    public VehicleId()
    {
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

    public VehicleId withSerialId(final Long serialId)
    {
        this.serialId = serialId;
        return this;
    }

    public VehicleId withPlateNumber(final String plateNumber)
    {
        this.plateNumber = plateNumber;
        return this;
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
