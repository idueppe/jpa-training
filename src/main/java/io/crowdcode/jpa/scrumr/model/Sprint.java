package io.crowdcode.jpa.scrumr.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sprint extends AbstractEntity
{

    private String name;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Double capacity;

    @ManyToOne
    private Project project;

    @ManyToMany
    private List<BacklogItem> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public String getName()
    {
        return name;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public Double getCapacity()
    {
        return capacity;
    }

    public Project getProject()
    {
        return project;
    }

    public List<BacklogItem> getItems()
    {
        return items;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }

    public Sprint withTasks(final List<Task> tasks)
    {
        this.tasks = tasks;
        return this;
    }

    public Sprint withItems(final List<BacklogItem> items)
    {
        this.items = items;
        return this;
    }

    public Sprint withProject(final Project project)
    {
        this.project = project;
        return this;
    }

    public Sprint withCapacity(final Double capacity)
    {
        this.capacity = capacity;
        return this;
    }

    public Sprint withEndDate(final Date endDate)
    {
        this.endDate = endDate;
        return this;
    }

    public Sprint withStartDate(final Date startDate)
    {
        this.startDate = startDate;
        return this;
    }

    public Sprint withName(final String name)
    {
        this.name = name;
        return this;
    }

    public Sprint addTasks(final Task task) {
        this.tasks.add(task);
        return this;
    }


}
