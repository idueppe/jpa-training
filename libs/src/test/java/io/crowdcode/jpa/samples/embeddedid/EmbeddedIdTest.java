package io.crowdcode.jpa.samples.embeddedid;

import io.crowdcode.jpa.AbstractJpaTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.Query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmbeddedIdTest extends AbstractJpaTest
{

    @Test
    public void test_1_add_products() throws Exception
    {
        LOG.info("Adding some product data to database");

        txBegin();

        for (long serialId=100; serialId < 110; serialId++) {
            em.persist(new Product().withModelName("PRODUCT"+serialId).withSerialId(serialId));
        }

        txCommit();

    }

    @Test
    public void test_2_add_vehicles() throws Exception
    {
        LOG.info("Adding some vehicle to product 1");

        txBegin();

        for (long id=1030; id < 1050; id++) {
            em.persist(new Vehicle().withVehicleId(new VehicleId().withPlateNumber("MS"+id).withSerialId(100l)).withModel("MODEL"+id));
        }

        txCommit();

    }

    @Test
    public void test_3_load_a_product() throws Exception {
        Product found = em.find(Product.class, 100l);
        System.out.println(found);
        LOG.info("Found: "+found);
        assertThat(found.getVehicles(), hasSize(20));
    }

    @Test
    public void test_4_move_vehicles_to_another_product() throws Exception
    {

        txBegin();
        // Primary Keys können nur effizient über JPQL Update verändert werden.
        // Ansonsten müsste die Entität zunächst gelöscht und dann neu persistiert werden.
        // Hierbei muss darauf geachtet werden, dass alle Referenzen übergegeben werden.
        String jpql = "UPDATE Vehicle v SET v.id.serialId = 101 WHERE v.id.serialId = :serialId AND v.id.plateNumber like :plateNumber ";
        Query query = em.createQuery(jpql);
        query.setParameter("serialId",100l);
        query.setParameter("plateNumber", "MS104_");
        query.executeUpdate();
        txCommit();

        Product product = em.find(Product.class, 101l);
        assertThat(product.getVehicles(), hasSize(10));

    }

    @Test
    public void test_5_double_check() throws Exception
    {
        Product found = em.find(Product.class, 100l);
        System.out.println(found);
        LOG.info("Found: "+found);
        assertThat(found.getVehicles(), hasSize(10));
    }


}
