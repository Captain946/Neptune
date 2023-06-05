package ManishLokesh.Neptune.v1.Outlets.Repository;

import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutletRepo extends JpaRepository<Outlet, Long> {
     Outlet saveAndFlush(Outlet outlet);

     Optional<Outlet> findById(Long Id);

}
