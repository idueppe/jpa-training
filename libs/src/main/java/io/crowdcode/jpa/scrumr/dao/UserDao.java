package io.crowdcode.jpa.scrumr.dao;

import io.crowdcode.jpa.scrumr.model.User;
import io.crowdcode.jpa.scrumr.model.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class UserDao
{
    private EntityManager em;

    public UserDao(EntityManager em)
    {
        this.em = em;
    }

    public User findByEmail(String email) throws UserNotFoundException
    {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);
        try
        {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            throw new UserNotFoundException(email, nre);
        }
    }

    public Collection<User> findNotInList(List<User> users) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_NOT_IN, User.class);
        query.setParameter("users", users);
        return query.getResultList();
    }

}
