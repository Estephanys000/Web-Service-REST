// src/main/java/mack/ps2/estagios/estagios/repositories/EstudanteRepo.java
// src/main/java/mack/ps2/estagios/estagios/repositories/EmpresaRepo.java
package mack.ps2.estagios.estagios.repositories;

import mack.ps2.estagios.estagios.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepo extends JpaRepository<Empresa, Long> {
}