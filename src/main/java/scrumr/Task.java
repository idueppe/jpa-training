package scrumr;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Task extends AbstractEntity
{

    private String title;

    @ManyToOne
    private BacklogItem item;

    public Task()
    {
    }

    public Task(String title, BacklogItem item)
    {
        this.title = title;
        this.item = item;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BacklogItem getItem()
    {
        return item;
    }

    public void setItem(BacklogItem item)
    {
        this.item = item;
    }
}
