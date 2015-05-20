package jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by idueppe on 20.05.15.
 */
public class Application4
{


    public static void main(String... args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        Kunde kunde = new Kunde();
        kunde.setId(5L);

        Address address = new Address("a","b","c");
        kunde.setAddress(address);

        System.out.println("Kunde:"+kunde);
        System.out.println("address:" + address);

        em.persist(kunde);


        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
