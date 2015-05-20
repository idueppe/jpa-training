package scrumr;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project extends AbstractEntity
{

    private String name;
    private String description;

    @ManyToOne
    @JoinTable(name = "project_owners")
    private User productOwner;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "master_user_id")
    private User scrumMaster;

    @ManyToMany
    private List<User> developers = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<BacklogItem> items = new ArrayList<>();

    public void addItem(BacklogItem item) {
        items.add(item);
        item.setProject(this);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public User getProductOwner()
    {
        return productOwner;
    }

    public void setProductOwner(User productOwner)
    {
        this.productOwner = productOwner;
    }

    public User getScrumMaster()
    {
        return scrumMaster;
    }

    public void setScrumMaster(User scrumMaster)
    {
        this.scrumMaster = scrumMaster;
    }

    public List<User> getDevelopers()
    {
        return developers;
    }

    public void setDevelopers(List<User> developers)
    {
        this.developers = developers;
    }

    public List<BacklogItem> getItems()
    {
        return items;
    }

    public void setItems(List<BacklogItem> items)
    {
        this.items = items;
    }
}
