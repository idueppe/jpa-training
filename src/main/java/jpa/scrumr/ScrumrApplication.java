package jpa.scrumr;

import jpa.scrumr.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
                                .addTasks(new Bug()
                                        .withBugReport("Exception xyz...")
                                        .withAssignTo(user)
                                        .withName("Fehler im QL")
                                        .withState(TaskState.TODO))
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

        em.close();
        emf.close();
    }

    public static class ReportTaskVisitor implements TaskVisitor {

        @Override
        public void visit(Task task)
        {
            System.out.println("Task"+task);
        }

        @Override
        public void visit(Issue issue)
        {
            System.out.println("Issue"+issue);
        }

        @Override
        public void visit(CompositeTask task)
        {
            System.out.println("CompositeTask"+task);
        }

        @Override
        public void visit(Bug bug)
        {
            System.out.println("Bug "+bug);
        }
    }

}
