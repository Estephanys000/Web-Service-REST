package mack.ps2.estagios.estagios.controller;

import mack.ps2.estagios.estagios.model.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaRepo empresaRepo;


    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        // 5. Mudar a busca de "stream()" para "empresaRepo.findById()"
        Optional<Empresa> empresaOptional = empresaRepo.findById(id);
        return empresaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        
        return empresaRepo.save(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        // 7. Mudar a lógica de atualização para usar o banco
        Optional<Empresa> empresaOptional = empresaRepo.findById(id);

        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setCnpj(empresaAtualizada.getCnpj());
            empresaExistente.setEmailContato(empresaAtualizada.getEmailContato());

            // Salva a entidade atualizada
            Empresa empresaSalva = empresaRepo.save(empresaExistente); 
            return ResponseEntity.ok(empresaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        // 8. Mudar a lógica de deleção
        if (empresaRepo.existsById(id)) {
            empresaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}