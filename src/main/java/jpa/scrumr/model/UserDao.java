package jpa.scrumr.model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

}
