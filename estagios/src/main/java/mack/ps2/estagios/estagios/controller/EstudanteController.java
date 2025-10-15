package mack.ps2.estagios.estagios.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EstudanteController {
    private List <Estudante> estudantes;

    // BLOCO STATIC

    public EstudanteController(){

        this.estudantes = new ArrayList();
    
        estudantes.add(new Estudante(1L, "Ana Paula Souza", "ana.souza@email.com", LocalDate.parse("2002-03-15"), 2020));
        estudantes.add(new Estudante(2L, "Carlos Henrique Lima", "carlos.lima@email.com", LocalDate.parse("2001-10-22"), 2019));
        estudantes.add(new Estudante(3L, "Fernanda Oliveira", "fernanda.oliveira@email.com", LocalDate.parse("2003-07-05"), 2021));
        estudantes.add(new Estudante(4L, "Lucas Pereira", "lucas.pereira@email.com", LocalDate.parse("2002-04-11"), 2020));
        estudantes.add(new Estudante(5L, "Gabriela Martins", "gabriela.martins@email.com", LocalDate.parse("2001-12-25"), 2019));
        estudantes.add(new Estudante(6L, "Rafael Costa", "rafael.costa@email.com", LocalDate.parse("2000-09-13"), 2018));
        estudantes.add(new Estudante(7L, "Juliana Silva", "juliana.silva@email.com", LocalDate.parse("2002-06-18"), 2020));
        estudantes.add(new Estudante(8L, "Marcos Vinícius", "marcos.vinicius@email.com", LocalDate.parse("2003-01-30"), 2021));
        estudantes.add(new Estudante(9L, "Camila Azevedo", "camila.azevedo@email.com", LocalDate.parse("2001-11-08"), 2019));
        estudantes.add(new Estudante(10L, "Felipe Cardoso", "felipe.cardoso@email.com", LocalDate.parse("2000-08-27"), 2018));
    
    }
    
    @GetMapping
    public List<Estudante> getAll() {
        return estudantes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> getById(@PathVariable Long id) {
        Optional<Estudante> estudante = estudantes.stream().filter(e -> e.getId().equals(id)).findFirst();
        return estudante.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudante create(@RequestBody Estudante novoEstudante) {
        novoEstudante.setId(proximoId++);
        estudantes.add(novoEstudante);
        return novoEstudante;
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> update(@PathVariable Long id, @RequestBody Estudante estudanteAtualizado) {
        Optional<Estudante> estudanteExistente = estudantes.stream().filter(e -> e.getId().equals(id)).findFirst();

        if (estudanteExistente.isPresent()) {
            Estudante estudante = estudanteExistente.get();
            
            estudante.setNome(estudanteAtualizado.getNome());
            estudante.setEmail(estudanteAtualizado.getEmail());
            estudante.setNascimento(estudanteAtualizado.getNascimento());
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