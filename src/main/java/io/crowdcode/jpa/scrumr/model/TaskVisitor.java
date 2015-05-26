package io.crowdcode.jpa.scrumr.model;

public interface TaskVisitor
{
    public void visit(Task task);

    public void visit(Issue issue);

    public void visit(CompositeTask task);

    public void visit(Bug bug);

}
