package jpa.scrumr.model;

import jpa.scrumr.model.Task;

import javax.persistence.Entity;

@Entity
public class Issue extends Task
{
    private String issueName;


}
