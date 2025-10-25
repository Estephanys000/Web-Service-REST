package mack.ps2.estagios.estagios.repositories;

import mack.ps2.estagios.estagios.model.Vaga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepo extends JpaRepository<Vaga, Long> {
}