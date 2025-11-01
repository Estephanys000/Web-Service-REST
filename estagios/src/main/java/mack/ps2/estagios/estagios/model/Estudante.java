package mack.ps2.estagios.estagios.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Estudante {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    private String nome;
    private String email;
    private LocalDate nascimento;
    private int anoIngresso;

    
    @ManyToOne
    @JoinColumn(name = "area_id") 
    private Area areaDeInteresse;

    
    public Estudante (Long id, String nome, String email, LocalDate dataNascimento, int anoIngresso, Area areaDeInteresse){
        this.ID = id;
        this.nome = nome;
        this.email = email;
        this.nascimento = dataNascimento;
        this.anoIngresso = anoIngresso;
        this.areaDeInteresse = areaDeInteresse;
    }

    
    public Estudante(){
       
    }

    
    public Long getId(){
        return ID;
    }

    public void setId(Long id){
        this.ID = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public LocalDate getDataNascimento(){
        return nascimento;
    }

    public void setDataNascimento(LocalDate nascimento){
        this.nascimento = nascimento;
    }

    public int getAnoIngresso(){
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso){
        this.anoIngresso = anoIngresso;
    }
    

    public Area getAreaDeInteresse() {
        return areaDeInteresse;
    }

    public void setAreaDeInteresse(Area areaDeInteresse) {
        this.areaDeInteresse = areaDeInteresse;
    }
}