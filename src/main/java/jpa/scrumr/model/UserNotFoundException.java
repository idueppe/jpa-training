package jpa.scrumr.model;

import javax.persistence.NoResultException;

public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String email, NoResultException nre)
    {
        super("User with "+email+" doesn't exist!", nre);
    }
}
