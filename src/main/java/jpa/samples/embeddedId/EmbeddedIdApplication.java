package jpa.samples.embeddedId;

import jpa.samples.foreignkeyrefs.ArbeitsUnfaehigkeit;
import jpa.samples.foreignkeyrefs.Grunddaten;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmbeddedIdApplication
{

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        VehicleId vid = new VehicleId().withPlateNumber("MS").withSerialId(123l);
        Vehicle vehicle = new Vehicle().withVehicleId(vid);
        Product product = new Product().withModelName("Auto").withSerialId(124l);


        em.getTransaction().begin();

        em.persist(vehicle);
        em.persist(product);


        em.getTransaction().commit();

        em.clear();

        Product found = em.find(Product.class, 123l);
        System.out.println(product.toString());

        em.close();
        emf.close();
    }



}
