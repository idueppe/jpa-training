package jpa.scrumr.model;

/**
 * Created by idueppe on 21.05.15.
 */
public interface TaskVisitor
{
    public void visit(Task task);

    public void visit(Issue issue);

    public void visit(CompositeTask task);

    public void visit(Bug bug);

}
