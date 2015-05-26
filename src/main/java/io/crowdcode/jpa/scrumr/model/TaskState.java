package io.crowdcode.jpa.scrumr.model;

public enum TaskState
{

    TODO(1),
    IN_PROGRESS(2),
    BLOCKED(3),
    REVIEW(4),
    DONE(5);

    private int dbKey;

    private TaskState(int dbKey) {
        this.dbKey = dbKey;
    }

    public int getDbKey()
    {
        return dbKey;
    }
}

