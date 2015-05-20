package scrumr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by idueppe on 20.05.15.
 */
@Entity
public class BacklogItem extends AbstractEntity
{
    private String title;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();


    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public BacklogItem()
    {
    }

    public BacklogItem(String title)
    {
        this.title = title;
    }

    public void addTask(String title) {
        tasks.add(new Task(title, this));
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
