package jpa.scrumr.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Identifiable<Long>
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

//    @PrePersist
//    public void beforePersist()
//    {
//        if (id == null || id.isEmpty()) {
//            id = UUID.randomUUID().toString();
//        }
//    }

}
