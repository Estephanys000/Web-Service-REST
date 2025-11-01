package mack.ps2.estagios.estagios.repositories;

import mack.ps2.estagios.estagios.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepo extends JpaRepository<Inscricao, Long> {
}