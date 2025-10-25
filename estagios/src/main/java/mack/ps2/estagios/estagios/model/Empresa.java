package mack.ps2.estagios.estagios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String nome;
    private String cnpj;
    private String email;
  

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

}
