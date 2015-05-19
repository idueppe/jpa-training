package jpa.demo;

import javax.persistence.EntityManager;

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

    public void persist(Kunde kunde) {
        em.persist(kunde);
    }

    public void remove(Kunde kunde) {
        em.remove(kunde);
    }

    public Kunde update(Kunde kunde) {
        return em.merge(kunde);
    }
}
