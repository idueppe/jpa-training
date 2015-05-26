package io.crowdcode.jpa.scrumr;

import io.crowdcode.jpa.AbstractJpaTest;
import io.crowdcode.jpa.scrumr.model.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScrumrSampleTest extends AbstractJpaTest
{

    @Test
    public void test_1_add_user_and_project() throws Exception
    {
        txBegin();
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


    }
}
