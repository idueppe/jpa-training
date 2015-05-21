package jpa.scrumr.model;

import jpa.scrumr.model.Task;

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
