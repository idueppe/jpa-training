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
        app.doPersistAndFind();
        app.close();
    }

    private void doPersistAndFind()
    {
        Kunde k = new Kunde();
        k.setId(1L);
        k.setName("Ingo Düppe");
        kundeDao.persist(k);

        refreshEM();

        Kunde found = kundeDao.find(1L);
        System.out.println("Kunde "+found);

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
