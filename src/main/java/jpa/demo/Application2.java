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

    public Application2()
    {
        emf = Persistence.createEntityManagerFactory("jpa");
        openEM();
    }

    public void refreshEM() {
        closeEM();
        openEM();
    }

    public void openEM()
    {
        em = emf.createEntityManager();
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

    public static void main(String[] args)
    {

    }
}
