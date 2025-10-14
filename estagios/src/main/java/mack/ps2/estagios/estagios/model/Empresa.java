package mack.ps2.estagios.estagios.model;

public class Empresa {
    private String nome;
    private String cnpj;
    private String email;
    private int ID;

    public Empresa(String nome, String cnpj, String email, int ID) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

}
