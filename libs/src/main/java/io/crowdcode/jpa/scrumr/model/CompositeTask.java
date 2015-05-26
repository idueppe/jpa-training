package io.crowdcode.jpa.scrumr.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by idueppe on 20.05.15.
 */
@Entity
@DiscriminatorValue("C")
public class CompositeTask extends Task
{
    @OneToMany
    private List<Task> subTasks;

    public List<Task> getSubTasks()
    {
        return subTasks;
    }

    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    public void setSubTasks(List<Task> subTasks)
    {
        this.subTasks = subTasks;
    }
}
