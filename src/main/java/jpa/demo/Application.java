package jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application
{

    public static void main(String[] args)
    {
//        Map properties = new HashMap<>();
//        properties.put("","");
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa");

        // EntityManager holen
        // EntityManager sind nicht ThreadSafe
        EntityManager em = emf.createEntityManager();


        Kunde kunde = new Kunde();
        kunde.setId(1L);
        kunde.setName("Ingo");
        // Transaktion starten
        em.getTransaction().begin();

        em.persist(kunde);

        kunde.setTitle("none");

        // Transaktion commiten
        em.getTransaction().commit();

        // Entity Manager schließen
        em.close();

        // Entity Manager Factory schließen
        emf.close();
    }

}
