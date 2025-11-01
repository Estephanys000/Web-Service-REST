package mack.ps2.estagios.estagios.repositories;

import mack.ps2.estagios.estagios.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepo extends JpaRepository<Area, Long> {
}