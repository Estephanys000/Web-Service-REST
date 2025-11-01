package mack.ps2.estagios.estagios.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInscricao;
    private String status;
    private String mensagemApresentacao;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    public Inscricao() {
        this.dataInscricao = LocalDate.now(); // Data de inscrição padrão
        this.status = "PENDENTE"; // Status padrão
    }

    public Inscricao(LocalDate dataInscricao, String status, String mensagemApresentacao, Estudante estudante, Vaga vaga) {
        this.dataInscricao = dataInscricao;
        this.status = status;
        this.mensagemApresentacao = mensagemApresentacao;
        this.estudante = estudante;
        this.vaga = vaga;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagemApresentacao() {
        return mensagemApresentacao;
    }

    public void setMensagemApresentacao(String mensagemApresentacao) {
        this.mensagemApresentacao = mensagemApresentacao;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}