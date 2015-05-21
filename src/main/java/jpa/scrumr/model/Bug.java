package jpa.scrumr.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Bug extends Issue
{

    private String bugReport;

    public String getBugReport()
    {
        return bugReport;
    }

    public Bug withBugReport(final String bugReport)
    {
        this.bugReport = bugReport;
        return this;
    }


    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }


}
