package mack.ps2.estagios.estagios.controller;

import mack.ps2.estagios.estagios.model.Vaga;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    private final List<Vaga> vagas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public VagaController() {
        vagas.add(new Vaga(counter.incrementAndGet(), "Desenvolvedor Java", "Atuação em projetos backend com Java e Spring. Experiência desejada em APIs REST.", LocalDate.of(2025, 10, 1), true, 1L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Analista de Suporte Técnico", "Suporte a clientes, resolução de chamados e participação em treinamentos internos.", LocalDate.of(2025, 9, 27), true, 2L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Engenheiro de Software", "Desenvolvimento de soluções para sistemas corporativos, integração e automação.", LocalDate.of(2025, 10, 3), false, 3L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Analista de Dados", "Manipulação e análise de grandes volumes de dados. Conhecimentos de SQL e Python.", LocalDate.of(2025, 9, 18), true, 4L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Designer Digital", "Criação de materiais gráficos, UX/UI e participação em campanhas de marketing.", LocalDate.of(2025, 9, 30), false, 5L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Consultor de Projetos", "Elaboração e acompanhamento de projetos empresariais e treinamentos.", LocalDate.of(2025, 10, 6), true, 1L));
        vagas.add(new Vaga(counter.incrementAndGet(), "Programador Full Stack", "Desenvolvimento de aplicações web frontend e backend com foco em automação.", LocalDate.of(2025, 10, 4), true, 2L));
    }

    @GetMapping
    public List<Vaga> getAllVagas() {
        return vagas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Long id) {
        return vagas.stream()
            .filter(v -> v.getId().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vaga createVaga(@RequestBody Vaga vaga) {
        vaga.setId(counter.incrementAndGet());
        vagas.add(vaga);
        return vaga;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        Optional<Vaga> vagaOptional = vagas.stream().filter(v -> v.getId().equals(id)).findFirst();

        if (vagaOptional.isPresent()) {
            Vaga vagaExistente = vagaOptional.get();
            vagaExistente.setTitulo(vagaAtualizada.getTitulo());
            vagaExistente.setDescricao(vagaAtualizada.getDescricao());
            vagaExistente.setPublicacao(vagaAtualizada.getPublicacao());
            vagaExistente.setAtivo(vagaAtualizada.isAtivo());
            vagaExistente.setIdEmpresa(vagaAtualizada.getIdEmpresa());
            return ResponseEntity.ok(vagaExistente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        if (vagas.removeIf(v -> v.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}