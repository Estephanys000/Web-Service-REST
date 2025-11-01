package mack.ps2.estagios.estagios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;
    private String cnpj;
    private String email;
    @OneToMany(mappedBy = "vagaEstagio")
    @JsonIgnore
    private Set<Inscricao> inscricoes;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private Set<Vaga> vagas;

    public Empresa() {
    }

    public Empresa(String nome, String cnpj, String email, Long ID) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(Set<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public Set<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(Set<Vaga> vagas) {
        this.vagas = vagas;
    }

}
