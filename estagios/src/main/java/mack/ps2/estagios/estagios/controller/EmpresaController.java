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
    private final List<Empresa> empresas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public EmpresaController() {
        // Carga inicial de dados
        empresas.add(new Empresa("Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com",1l));
        empresas.add(new Empresa("Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com",2l));
        empresas.add(new Empresa("Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com",3l));
        empresas.add(new Empresa("Delta Engenharia", "22.333.444/0001-55", "contato@deltaeng.com",4l));
        empresas.add(new Empresa( "Epsilon Digital", "33.444.555/0001-66", "email@epsilondigital.com",5l));
    }

    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long ID) {
        Optional<Empresa> empresaOptional = empresas.stream().filter(e -> e.getID().equals(ID)).findFirst();
        return empresaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa) {
        empresa.setID(counter.incrementAndGet());
        empresas.add(empresa);
        return empresa;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long ID, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresaOptional = empresas.stream().filter(e -> e.getID().equals(ID)).findFirst();

        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setCnpj(empresaAtualizada.getCnpj());
            empresaExistente.setEmail(empresaAtualizada.getEmail());
            return ResponseEntity.ok(empresaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable long ID) {
        boolean removed = empresas.removeIf(e -> e.getID().equals(ID));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}