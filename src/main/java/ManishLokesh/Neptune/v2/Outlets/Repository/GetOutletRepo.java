package ManishLokesh.Neptune.v2.Outlets.Repository;

import ManishLokesh.Neptune.v1.Outlets.Entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetOutletRepo extends JpaRepository<Outlet, Long> {
    List<Outlet> findByStationCode(String stationCode);

}
