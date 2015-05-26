package io.crowdcode.jpa.scrumr.visitor;

import io.crowdcode.jpa.scrumr.model.*;

public class ReportTaskVisitor implements TaskVisitor
{

    @Override
    public void visit(Task task)
    {
        System.out.println("Task" + task);
    }

    @Override
    public void visit(Issue issue)
    {
        System.out.println("Issue" + issue);
    }

    @Override
    public void visit(CompositeTask task)
    {
        System.out.println("CompositeTask" + task);
    }

    @Override
    public void visit(Bug bug)
    {
        System.out.println("Bug " + bug);
    }
}
