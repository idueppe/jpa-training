package jpa.demo;

import jpa.demo.ids.Vehicle;
import jpa.demo.ids.VehicleId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by idueppe on 20.05.15.
 */
public class Application5
{


    public static void main(String... args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        VehicleId id = new VehicleId(1l,"MS");

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(id);
        vehicle.setModel("A4");

        em.persist(vehicle);

        em.getTransaction().commit();

        em.clear();

        Vehicle found = em.find(Vehicle.class, id);
        System.out.println(found);

        em.close();
        emf.close();

    }
}
