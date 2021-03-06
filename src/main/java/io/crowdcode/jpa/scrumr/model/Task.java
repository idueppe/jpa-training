package io.crowdcode.jpa.scrumr.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR)
//@DiscriminatorValue(value = "T")
public class Task extends AbstractEntity
{

    private String name;
    private String description;
    private Double estimation;

    @Basic(fetch = FetchType.LAZY)
    private Double remaining;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    @ManyToOne(fetch = FetchType.LAZY)
    private BacklogItem backlogItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedTo;

    @ManyToOne
    private BacklogItem item;


    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getEstimation()
    {
        return estimation;
    }

    public Double getRemaining()
    {
        return remaining;
    }

    public TaskState getState()
    {
        return state;
    }

    public BacklogItem getBacklogItem()
    {
        return backlogItem;
    }

    public BacklogItem getItem()
    {
        return item;
    }

    public Task withItem(final BacklogItem item)
    {
        this.item = item;
        return this;
    }

    public Task withBacklogItem(final BacklogItem backlogItem)
    {
        this.backlogItem = backlogItem;
        return this;
    }

    public Task withState(final TaskState state)
    {
        this.state = state;
        return this;
    }

    public Task withRemaining(final Double remaining)
    {
        this.remaining = remaining;
        return this;
    }

    public Task withEstimation(final Double estimation)
    {
        this.estimation = estimation;
        return this;
    }

    public Task withDescription(final String description)
    {
        this.description = description;
        return this;
    }

    public Task withName(final String name)
    {
        this.name = name;
        return this;
    }

    public Task withAssignTo(final User user)
    {
        this.assignedTo = user;
        return this;
    }


}
