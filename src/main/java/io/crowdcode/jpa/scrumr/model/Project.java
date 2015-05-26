package io.crowdcode.jpa.scrumr.model;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries(
        value = {@NamedQuery(
                name = Project.PROJECTS_WITH_ASSIGNED_TASKS,
//                query = "SELECT p FROM Project p WHERE p.sprints.tasks.assignedTo = :user"
                query = "SELECT p FROM Project p LEFT JOIN p.sprints s LEFT JOIN s.tasks t WHERE t.assignedTo = :user"
        ), @NamedQuery(
        name = Project.PROJECTS_WITH_JOINFETCH,
        query = "SELECT DISTINCT p FROM Project p " +
                " LEFT JOIN FETCH p.items " +
                " LEFT JOIN FETCH p.scrumMaster WHERE p.name = :name"
        ), @NamedQuery(
                name = Project.PROJECTS_WITHOUT_JOINFETCH,
                query = "SELECT p FROM Project p WHERE p.name = :name"
        )})
public class Project extends AbstractEntity
{
    public static final String PROJECTS_WITH_ASSIGNED_TASKS = "Project.withAssignedTasks";
    public static final String PROJECTS_WITH_JOINFETCH      = "Project.PROJECTS_WITH_JOINFETCH";
    public static final String PROJECTS_WITHOUT_JOINFETCH   = "Project.PROJECTS_WITHOUT_JOINFETCH";

    private String name;
    private String description;

    @ManyToOne
    private User productOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    private User scrumMaster;

    @ManyToMany
    private List<User> developers = new ArrayList<>();

    //    mappedBy = "project",
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_items")
//    private List<BacklogItem> items = new ArrayList<>();
    private Set<BacklogItem> items = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Sprint> sprints = new ArrayList<>();

    public Project addItem(BacklogItem item)
    {
        items.add(item);
        item.setProject(this);
        return this;
    }

    public Project addSprint(Sprint sprint)
    {
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

    public Collection<BacklogItem> getItems()
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

    public Project withSprints(final List<Sprint> sprints)
    {
        this.sprints = sprints;
        return this;
    }

    @Override
    public String toString()
    {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productOwner=" + productOwner +
                ", scrumMaster=" + scrumMaster +
                ", developers=" + developers +
                ", items=" + items +
                ", sprints=" + sprints +
                '}';
    }
}
