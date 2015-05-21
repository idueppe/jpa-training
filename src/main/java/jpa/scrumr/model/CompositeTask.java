package jpa.scrumr.model;

import jpa.scrumr.model.Task;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by idueppe on 20.05.15.
 */
@Entity
public class CompositeTask extends Task
{
    @OneToMany
    private List<Task> subTasks;

    public List<Task> getSubTasks()
    {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks)
    {
        this.subTasks = subTasks;
    }
}
