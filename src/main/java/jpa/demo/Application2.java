package jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by idueppe on 19.05.15.
 */
public class Application2
{

    private EntityManagerFactory emf;

    private EntityManager em;

    private KundeDao kundeDao;

    public Application2()
    {
        emf = Persistence.createEntityManagerFactory("jpa");
        openEM();
    }


    public static void main(String[] args)
    {
        Application2 app = new Application2();
        app.doPersistAndFindWithNewEM();
        app.doPersistAndFindWithSameEM();
        app.doFindByEmail();
        app.doPersistAndRollbackAndSomething();
        app.close();
    }

    private void doPersistAndRollbackAndSomething() {
        beginTx();
        Kunde k = kundeDao.find(1L);
        k.setEmail("geändert");
//        em.flush();
        rollbackTx();
        beginTx();
        System.out.println("Kunde " + k);
        System.out.println("Kunde wird gemanaged: "+em.contains(k));
        Kunde merged = em.merge(k);
        em.flush();
//        em.createNativeQuery("UPDATE Kunde SET title='XYZ' WHERE ID = 1").executeUpdate();
        em.refresh(merged);
        System.out.println("Kunde " + k);
        System.out.println("Kunde " + merged);
        commitTx();
    }

    private void rollbackTx()
    {
        em.getTransaction().rollback();
    }

    private void doPersistAndFindWithNewEM()
    {
        beginTx();
        Kunde k = new Kunde();
        k.setId(1L);
        k.setName("Ingo Düppe");
        k.setEmail("ingo.dueppe@dueppe.com");
        kundeDao.persist(k);
        commitTx();

        refreshEM();

        Kunde found = kundeDao.find(1L);
        System.out.println("Kunde "+found);

    }

    private void doPersistAndFindWithSameEM()
    {
        beginTx();
        Kunde k = new Kunde();
        k.setId(2L);
        k.setName("Ingo Düppe");
        k.setEmail("ingo.dueppe@crowdcode.de");
        kundeDao.persist(k);
        commitTx();

        Kunde found2 = kundeDao.find(2L);
        System.out.println("Kunde 2:" + found2);
        System.out.println("Kunde 2:" + found2.getClass().getName());
        em.clear();

        Kunde found1 = kundeDao.reference(1L);
        System.out.println("Kunde 1:"+found1.getClass().getName());
        System.out.println("Kunde 1:"+found1);
    }

    private void doFindByEmail() {
        em.clear();
        Kunde kunde = kundeDao.findByEmail("ingo.dueppe@dueppe.com");
        System.out.println("Found: "+kunde);
    }

    private void commitTx()
    {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void beginTx()
    {
        em.getTransaction().begin();
    }

    public void refreshEM() {
        closeEM();
        openEM();
    }

    public void openEM()
    {
        em = emf.createEntityManager();
        kundeDao = new KundeDao(em);
    }

    public void close() {
        closeEM();
        closeEMF();
    }

    public void closeEMF()
    {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void closeEM()
    {
        if (em != null && em.isOpen())
        {
            em.close();
        }
    }

}