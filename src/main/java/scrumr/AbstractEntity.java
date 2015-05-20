package scrumr;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class AbstractEntity
{

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Timestamp lastUpdate;

    public Long getId()
    {
        return id;
    }

}
