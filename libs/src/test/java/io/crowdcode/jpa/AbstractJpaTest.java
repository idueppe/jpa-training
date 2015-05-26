package io.crowdcode.jpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public abstract class AbstractJpaTest
{
    protected static final Logger LOG = Logger.getLogger("JPA UNIT TEST:");

    protected static EntityManagerFactory emf;
    protected        EntityManager        em;

    @BeforeClass
    public static void setupClass()
    {
        LOG.info("Opening EntityManagerFactory ");
        emf = Persistence.createEntityManagerFactory("jpa");
    }

    @AfterClass
    public static void tearDownClass()
    {
        if (emf != null && emf.isOpen())
        {
            LOG.info("Closing EntityManagerFactory");
            emf.close();
        }
    }

    @Before
    public void setup()
    {
        LOG.info("Opening EntityManagerFactory");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown()
    {
        if (em.isOpen() && em.getTransaction().isActive())
        {
            LOG.info("Commiting open transaction");
            em.getTransaction().commit();
        }
        if (em.isOpen())
        {
            LOG.info("Closing open EntityManager");
            em.close();
        }
    }

    public void txBegin() {
        if (!em.getTransaction().isActive()) {
            LOG.info("Starting new Transaction");
            em.getTransaction().begin();
        } else {
            LOG.warning("Transaction already active!");
        }
    }

    public void txRollback() {
        if (em.getTransaction().isActive()) {
            LOG.info("Rolling back Transaction");
            em.getTransaction().rollback();
        } else {
            LOG.warning("No active Transaction that could be rollbacked!");
        }
    }

    public void txCommit() {
        if (em.getTransaction().isActive()) {
            LOG.info("Committing active Transaction");
            em.getTransaction().commit();
        } else {
            LOG.warning("No active Transaction that could be committed!");
        }
    }
}
