package ManishLokesh.Neptune.v2.Orders.Repository;


import ManishLokesh.Neptune.v2.Orders.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    public Orders saveAndFlush(Orders orders);
}
