// src/main/java/mack/ps2/estagios/estagios/controllers/EstudanteController.java
package mack.ps2.estagios.estagios.controllers;

import mack.ps2.estagios.estagios.models.Estudante;
import mack.ps2.estagios.estagios.repositories.EstudanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepo estudanteRepo;

    @GetMapping
    public List<Estudante> getAllEstudantes() {
        return estudanteRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> getEstudanteById(@PathVariable Long id) {
        return estudanteRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudante createEstudante(@RequestBody Estudante estudante) {
        return estudanteRepo.save(estudante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> updateEstudante(@PathVariable Long id, @RequestBody Estudante estudanteAtualizado) {
        
        Optional<Estudante> estudanteOptional = estudanteRepo.findById(id);

        if (estudanteOptional.isPresent()) {
            Estudante estudanteExistente = estudanteOptional.get();
            estudanteExistente.setNome(estudanteAtualizado.getNome());
            estudanteExistente.setEmail(estudanteAtualizado.getEmail());
            estudanteExistente.setNascimento(estudanteAtualizado.getNascimento());
            estudanteExistente.setAnoIngresso(estudanteAtualizado.getAnoIngresso());

            Estudante estudanteSalvo = estudanteRepo.save(estudanteExistente);
            return ResponseEntity.ok(estudanteSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudante(@PathVariable Long id) {
        
        if (estudanteRepo.existsById(id)) {
            estudanteRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}