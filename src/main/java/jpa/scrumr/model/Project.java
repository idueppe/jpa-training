package jpa.scrumr.model;

import jpa.scrumr.model.AbstractEntity;
import jpa.scrumr.model.BacklogItem;
import jpa.scrumr.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project extends AbstractEntity
{

    private String name;
    private String description;

    @ManyToOne
    private User productOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    private User scrumMaster;

    @ManyToMany
    private List<User> developers = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BacklogItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Sprint> sprints = new ArrayList<>();

    public Project addItem(BacklogItem item)
    {
        items.add(item);
        item.setProject(this);
        return this;
    }

    public Project addSprint(Sprint sprint) {
        sprints.add(sprint);
        sprint.withProject(this);
        return this;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public User getProductOwner()
    {
        return productOwner;
    }

    public User getScrumMaster()
    {
        return scrumMaster;
    }

    public List<User> getDevelopers()
    {
        return developers;
    }

    public List<BacklogItem> getItems()
    {
        return items;
    }

    public List<Sprint> getSprints()
    {
        return sprints;
    }

    public Project withName(final String name)
    {
        this.name = name;
        return this;
    }

    public Project withDescription(final String description)
    {
        this.description = description;
        return this;
    }

    public Project withProductOwner(final User productOwner)
    {
        this.productOwner = productOwner;
        return this;
    }

    public Project withScrumMaster(final User scrumMaster)
    {
        this.scrumMaster = scrumMaster;
        return this;
    }

    public Project withDevelopers(final List<User> developers)
    {
        this.developers = developers;
        return this;
    }

    public Project withItems(final List<BacklogItem> items)
    {
        this.items = items;
        return this;
    }

    public Project withSprints(final List<Sprint> sprints)
    {
        this.sprints = sprints;
        return this;
    }

}
