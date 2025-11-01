package mack.ps2.estagios.estagios.controller;

import mack.ps2.estagios.estagios.model.Vaga;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import mack.ps2.estagios.estagios.repositories.VagaRepo;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepo vagaRepo;

    @GetMapping
    public List<Vaga> getAllVagas() {
        return vagaRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        return vagaRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vaga createVaga(@RequestBody Vaga vaga) {
        return vagaRepo.save(vaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
    
        Optional<Vaga> vagaOptional = vagaRepo.findById(id);

        if (vagaOptional.isPresent()) {
            Vaga vagaExistente = vagaOptional.get();
            vagaExistente.setTitulo(vagaAtualizada.getTitulo());
            vagaExistente.setDescricao(vagaAtualizada.getDescricao());
            vagaExistente.setDataPublicacao(vagaAtualizada.getDataPublicacao());
            vagaExistente.setAtivo(vagaAtualizada.isAtivo());

            Vaga vagaSalva = vagaRepo.save(vagaExistente);
            return ResponseEntity.ok(vagaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        if (vagaRepo.existsById(id)) {
            vagaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}