package ManishLokesh.Neptune.v1.Outlets.Repository;

import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletRepo extends JpaRepository<Outlet, Long> {
    public Outlet saveAndFlush(Outlet outlet);
}
