package ManishLokesh.Neptune.v1.Menu.Repository;

import ManishLokesh.Neptune.v1.Menu.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    public Menu saveAndFlush(Menu menu);
}
