package io.crowdcode.jpa.scrumr;

import io.crowdcode.jpa.scrumr.model.*;
import io.crowdcode.jpa.scrumr.visitor.ReportTaskVisitor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ScrumrApplication
{

    public static void main(String... args) throws UserNotFoundException
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        User user = new User().withEmail("productowner@io.crowdcode.jpa.scrumr");

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
                                .addTasks(new Bug()
                                        .withBugReport("Exception xyz...")
                                        .withAssignTo(user)
                                        .withName("Fehler im QL")
                                        .withState(TaskState.TODO))
                );

        em.getTransaction().begin();

        em.persist(project);
        em.persist(user);

        for (int i = 0; i < 100; i++)
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

        em.clear();

        List<Task> tasks = em.createQuery("SELECT t FROM Task t",Task.class).getResultList();


        TaskVisitor visitor = new ReportTaskVisitor();

        for(Task task : tasks) {
            System.out.println("Task "+task.getClass()+" :"+ task);
            task.accept(visitor);
//            if (task instanceof Bug) {
//                Bug bug = (Bug) task;
//                System.out.println(bug.getBugReport());
//            }
        }

        UserDao dao = new UserDao(em);
        User productOwner = dao.findByEmail("productowner@io.crowdcode.jpa.scrumr");
        System.out.println(productOwner);


        em.clear();

        em.getTransaction().begin();

        em.persist(new User().withEmail("scrummaster@scrumr"));
        em.persist(new User().withEmail("developer@scrumr"));
        em.persist(new User().withEmail("teamlead@scrumr"));
        em.persist(new User().withEmail("coffee@scrumr"));

        em.getTransaction().commit();

        em.clear();

        User scrumMaster = dao.findByEmail("scrummaster@scrumr");

        Collection<User> users = dao.findNotInList(Arrays.asList(user, scrumMaster));
        for (User u : users)
        {
            System.out.println(u);
        }


        em.clear();
        TypedQuery<Project> query = em.createNamedQuery(Project.PROJECTS_WITH_JOINFETCH, Project.class);
        query.setParameter("name", "JPA Scrumr");
        for(Project p : query.getResultList())
            System.out.println(p);

        em.clear();
        query = em.createNamedQuery(Project.PROJECTS_WITHOUT_JOINFETCH, Project.class);
        query.setParameter("name", "JPA Scrumr");
        for(Project p : query.getResultList())
        {
            System.out.println(p);
            System.out.println(Arrays.toString(p.getItems().toArray()));
        }

        em.clear();


        em.getTransaction().begin();
        Project lockedProject = em.find(Project.class, project.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
//        lockedProject.withName("optimistic_force_increment");

        em.getTransaction().commit();

        em.clear();


        em.getTransaction().begin();
        Project lockedProject2 = em.find(Project.class, project.getId(), LockModeType.OPTIMISTIC);
//        lockedProject2.withName("optimistic_force_increment");

        em.getTransaction().commit();

        em.clear();


        em.getTransaction().begin();
        Project lockedProject3 = em.find(Project.class, project.getId(), LockModeType.PESSIMISTIC_READ);
        lockedProject3.withName("pessimistic read");

        em.getTransaction().commit();

        em.clear();


        em.getTransaction().begin();
        Project lockedProject4 = em.find(Project.class, project.getId(), LockModeType.PESSIMISTIC_WRITE);
        lockedProject4.withName("pessimistic read");

        em.getTransaction().commit();

        em.clear();


        em.getTransaction().begin();
        Project lockedProject5 = em.find(Project.class, project.getId(), LockModeType.PESSIMISTIC_FORCE_INCREMENT);
//        lockedProject5.withName("pessimistic read");

        em.lock(lockedProject5, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em.getTransaction().commit();



        em.clear();

        TypedQuery<BacklogItem> itemQuery = em.createQuery("SELECT b FROM BacklogItem b ", BacklogItem.class);
        itemQuery.setFirstResult(20);
        itemQuery.setMaxResults(20);
        List<BacklogItem> items =  itemQuery.getResultList();


        em.close();
        emf.close();
    }

}
