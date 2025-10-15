package mack.ps2.estagios.estagios.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mack.ps2.estagios.estagios.model.Estudantes;

@RestController
public class EstudanteController {
    private List <Estudantes> estudantes;

    public EstudanteController(){

        this.estudantes = new ArrayList();
    
        estudantes.add(new Estudantes(1L, "Ana Paula Souza", "ana.souza@email.com", LocalDate.parse("2002-03-15"), 2020));
        estudantes.add(new Estudantes(2L, "Carlos Henrique Lima", "carlos.lima@email.com", LocalDate.parse("2001-10-22"), 2019));
        estudantes.add(new Estudantes(3L, "Fernanda Oliveira", "fernanda.oliveira@email.com", LocalDate.parse("2003-07-05"), 2021));
        estudantes.add(new Estudantes(4L, "Lucas Pereira", "lucas.pereira@email.com", LocalDate.parse("2002-04-11"), 2020));
        estudantes.add(new Estudantes(5L, "Gabriela Martins", "gabriela.martins@email.com", LocalDate.parse("2001-12-25"), 2019));
        estudantes.add(new Estudantes(6L, "Rafael Costa", "rafael.costa@email.com", LocalDate.parse("2000-09-13"), 2018));
        estudantes.add(new Estudantes(7L, "Juliana Silva", "juliana.silva@email.com", LocalDate.parse("2002-06-18"), 2020));
        estudantes.add(new Estudantes(8L, "Marcos Vinícius", "marcos.vinicius@email.com", LocalDate.parse("2003-01-30"), 2021));
        estudantes.add(new Estudantes(9L, "Camila Azevedo", "camila.azevedo@email.com", LocalDate.parse("2001-11-08"), 2019));
        estudantes.add(new Estudantes(10L, "Felipe Cardoso", "felipe.cardoso@email.com", LocalDate.parse("2000-08-27"), 2018));
    
    }
    
    @GetMapping
    public List<Estudantes> getAll() {
        return estudantes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudantes> getById(@PathVariable Long id) {
        Optional<Estudante> estudante = estudantes.stream().filter(e -> e.getId().equals(id)).findFirst();
        return estudante.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudantes create(@RequestBody Estudantes novoEstudante) {
        int proximoId;
        novoEstudante.setId(proximoId++);
        estudantes.add(novoEstudante);
        return novoEstudante;
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Estudantes> update(@PathVariable Long id, @RequestBody Estudantes estudanteAtualizado) {
        Optional<Estudantes> estudanteExistente = estudantes.stream().filter(e -> e.getId().equals(id)).findFirst();

        if (estudanteExistente.isPresent()) {
            Estudantes estudante = estudanteExistente.get();
            
            estudante.setNome(estudanteAtualizado.getNome());
            estudante.setEmail(estudanteAtualizado.getEmail());
            estudante.setNascimento(estudanteAtualizado.getDataNascimento());
            estudante.setAnoIngresso(estudanteAtualizado.getAnoIngresso());
            return ResponseEntity.ok(estudante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removido = estudantes.removeIf(e -> e.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}