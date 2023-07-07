package ManishLokesh.Neptune.v2.Outlets.Repository;

import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetOutletRepo extends JpaRepository<Outlet, Long> {

}
