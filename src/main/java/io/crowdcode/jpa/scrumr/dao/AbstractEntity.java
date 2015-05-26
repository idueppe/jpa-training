package io.crowdcode.jpa.scrumr.dao;

import io.crowdcode.jpa.scrumr.model.Identifiable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Identifiable<String>
{

    @Id
//    @GeneratedValue
//    private Long id;
    private String id;

    @Version
    private Long version;
//    private Timestamp lastUpdate;

//    public Long getId()
//    {
//        return id;
//    }



    @Override
    public String getId()
    {
        return id;
    }

    @PrePersist
    public void beforePersist()
    {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }

}
