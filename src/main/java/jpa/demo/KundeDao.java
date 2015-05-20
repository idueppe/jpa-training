package jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KundeDao
{
    private EntityManager em;

    public KundeDao(EntityManager em)
    {
        this.em = em;
    }

    public Kunde find(Long id) {
        return em.find(Kunde.class, id);
    }

    public Kunde reference(Long id) {
        return em.getReference(Kunde.class, id);
    }

    public void persist(Kunde kunde) {
        em.persist(kunde);
    }

    public void remove(Kunde kunde) {
        em.remove(kunde);
    }

    public Kunde update(Kunde kunde) {
        return em.merge(kunde);
    }

    public List<Kunde> findAll() {
        TypedQuery<Kunde> query = em.createQuery("SELECT k FROM Kunde k", Kunde.class);
        return query.getResultList();
    }

    public Kunde findByEmail(String email) {
        TypedQuery<Kunde> query = em.createQuery("SELECT k FROM Kunde k WHERE k.email = :email", Kunde.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
