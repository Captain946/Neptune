package ManishLokesh.Neptune.Outlets.Repository;

import ManishLokesh.Neptune.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletRepo extends JpaRepository<Outlet, Long> {
    boolean existsById(Long id);

    
}
