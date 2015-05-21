package jpa.samples.foreignkeyrefs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.scrumr.model.*;

import java.util.List;

public class RefsApplication
{

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Grunddaten grund1 = new Grunddaten().withVnr("vnr1");
        Grunddaten grund2 = new Grunddaten().withVnr("vnr2");

        ArbeitsUnfaehigkeit au1 = new ArbeitsUnfaehigkeit().withGrunddaten(grund1);
        ArbeitsUnfaehigkeit au2 = new ArbeitsUnfaehigkeit().withGrunddaten(grund1);
        ArbeitsUnfaehigkeit au3 = new ArbeitsUnfaehigkeit().withGrunddaten(grund2);

        em.getTransaction().begin();

        em.persist(grund1);
        em.persist(grund2);

        em.persist(au1);
        em.persist(au2);
        em.persist(au3);

        em.getTransaction().commit();

        em.clear();

        ArbeitsUnfaehigkeit au = em.find(ArbeitsUnfaehigkeit.class, au1.getId());

        em.close();
        emf.close();
    }



}
