package scrumr;

import jpa.demo.ids.Vehicle;
import jpa.demo.ids.VehicleId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by idueppe on 20.05.15.
 */
public class ScrumrApplication
{


    public static void main(String... args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        User user = new User();
        user.setEmail("productowner@scrumr");

        Project project = new Project();
        project.setProductOwner(user);

        em.getTransaction().begin();

        em.persist(project);
        em.persist(user);

        for (int i = 0; i < 100; i++)
        {
            BacklogItem item = new BacklogItem("Issue " + i);
            item.addTask("A");
            item.addTask("B");
            item.addTask("C");
            project.addItem(item);
        }

        for(BacklogItem item : project.getItems()) {
            em.persist(item);
        }

        em.getTransaction().commit();

        em.clear();

        em.getTransaction().begin();

        Project found = em.find(Project.class, project.getId());
        found.addItem(new BacklogItem("NEUER FEHLER"));

        em.getTransaction().commit();


        em.close();
        emf.close();

    }
}
