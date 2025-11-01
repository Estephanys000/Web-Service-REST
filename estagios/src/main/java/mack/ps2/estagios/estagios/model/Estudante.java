package mack.ps2.estagios.estagios.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String nome;
    private String email;
    private int anoIngresso;

    @ManyToMany
    @JoinTable(name = "estudante_area", joinColumns = @JoinColumn(name = "estudante_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Set<Area> areasDeInteresse;

    public Estudante(Long id, String nome, String email, int anoIngresso) {
        this.ID = id;
        this.nome = nome;
        this.email = email;
        this.anoIngresso = anoIngresso;
    }

    public Estudante() {

    }

    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public Set<Area> getAreasDeInteresse() {
        return areasDeInteresse;
    }

    public void setAreasDeInteresse(Set<Area> areasDeInteresse) {
        this.areasDeInteresse = areasDeInteresse;
    }
}