package io.crowdcode.jpa.scrumr.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class Issue extends Task
{
    private String issueName;


    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }
}
