package mack.ps2.estagios.estagios.controller;

import mack.ps2.estagios.estagios.model.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import mack.ps2.estagios.estagios.repositories.EmpresaRepo;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaRepo empresaRepo;


    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
       
        Optional<Empresa> empresaOptional = empresaRepo.findById(id);
        return empresaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        
        return empresaRepo.save(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
 
        Optional<Empresa> empresaOptional = empresaRepo.findById(id);

        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNomeFantasia(empresaAtualizada.getNomeFantasia());
            empresaExistente.setCnpj(empresaAtualizada.getCnpj());
            empresaExistente.setEmail(empresaAtualizada.getEmail());
            empresaExistente.setEndereco(empresaAtualizada.getEndereco());
            empresaExistente.setDescricao(empresaAtualizada.getDescricao());


            Empresa empresaSalva = empresaRepo.save(empresaExistente); 
            return ResponseEntity.ok(empresaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
     
        if (empresaRepo.existsById(id)) {
            empresaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}