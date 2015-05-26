package io.crowdcode.jpa.scrumr.model;

import javax.persistence.*;

@Entity
public class BacklogItem extends AbstractEntity
{
    private String name;

    private String userStory;

    private Double priority;

    private Double estimate;

    @ManyToOne
    private Project project;

    public BacklogItem()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUserStory()
    {
        return userStory;
    }

    public void setUserStory(String userStory)
    {
        this.userStory = userStory;
    }

    public Double getPriority()
    {
        return priority;
    }

    public void setPriority(Double priority)
    {
        this.priority = priority;
    }

    public Double getEstimate()
    {
        return estimate;
    }

    public void setEstimate(Double estimate)
    {
        this.estimate = estimate;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public BacklogItem withProject(final Project project)
    {
        this.project = project;
        return this;
    }

    public BacklogItem withEstimate(final Double estimate)
    {
        this.estimate = estimate;
        return this;
    }

    public BacklogItem withPriority(final Double priority)
    {
        this.priority = priority;
        return this;
    }

    public BacklogItem withUserStory(final String userStory)
    {
        this.userStory = userStory;
        return this;
    }

    public BacklogItem withName(final String name)
    {
        this.name = name;
        return this;
    }


}
