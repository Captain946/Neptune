package ManishLokesh.Neptune.v1.Outlets.Repository;

import ManishLokesh.Neptune.v1.Outlets.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    public Menu saveAndFlush(Menu menu);
}
