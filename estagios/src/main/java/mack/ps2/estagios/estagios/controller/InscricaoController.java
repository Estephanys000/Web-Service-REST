package mack.ps2.estagios.estagios.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import mack.ps2.estagios.estagios.model.Inscricao;
import mack.ps2.estagios.estagios.repositories.InscricaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoRepo inscricaoRepo;

    @GetMapping
    public List<Inscricao> getAllInscricoes() {
        return inscricaoRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> getInscricaoById(@PathVariable Long id) {
        return inscricaoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inscricao createInscricao(@RequestBody Inscricao inscricao) {
        return inscricaoRepo.save(inscricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> updateInscricao(@PathVariable Long id, @RequestBody Inscricao inscricaoAtualizada) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepo.findById(id);

        if (inscricaoOptional.isPresent()) {
            Inscricao inscricaoExistente = inscricaoOptional.get();
            inscricaoExistente.setStatus(inscricaoAtualizada.getStatus());
            inscricaoExistente.setMensagemApresentacao(inscricaoAtualizada.getMensagemApresentacao());


            Inscricao inscricaoSalva = inscricaoRepo.save(inscricaoExistente);
            return ResponseEntity.ok(inscricaoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscricao(@PathVariable Long id) {
        if (inscricaoRepo.existsById(id)) {
            inscricaoRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}