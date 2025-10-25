package mack.ps2.estagios.estagios.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estudante {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;
    private String email;
    private LocalDate nascimento;
    private int anoIngresso;

    //MÉTODO CONSTRUTOR

    public Estudante (Long id, String nome, String email, LocalDate dataNascimento, int anoIngresso){
        this.ID = id;
        this.nome = nome;
        this.email = email;
        this.nascimento = dataNascimento;
        this.anoIngresso = anoIngresso;
    }

    //CONSTRUTOR VAZIO

    public Estudante(){

    }

    //GET E SET

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



}