package jpa.scrumr;

import jpa.scrumr.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ScrumrApplication
{

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        User user = new User();
        user.setEmail("productowner@jpa.scrumr");

        Project project = new Project()
                .withName("JPA Scrumr")
                .withProductOwner(user)
                .addItem(new BacklogItem().withName("A"))
                .addItem(new BacklogItem().withName("B"))
                .addSprint(new Sprint()
                                .withName("Sprint A")
                                .addTasks(new Task()
                                                .withName("Analyse")
                                                .withState(TaskState.TODO)
                                                .withAssignTo(user)
                                )
                );

        em.getTransaction().begin();

        em.persist(project);
        em.persist(user);

        for (int i = 0; i < 10; i++)
        {
            BacklogItem item = new BacklogItem().withName("Issue "+i);
            project.addItem(item);
        }

        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();

        Project found = em.find(Project.class, project.getId());
        found.addItem(new BacklogItem().withName("New Issue"));

        em.getTransaction().commit();

        em.close();
        emf.close();
    }



}
