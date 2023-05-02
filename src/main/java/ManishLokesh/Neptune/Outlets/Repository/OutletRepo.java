package ManishLokesh.Neptune.Outlets.Repository;

import ManishLokesh.Neptune.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutletRepo extends JpaRepository<Outlet, Long> {
    
}
