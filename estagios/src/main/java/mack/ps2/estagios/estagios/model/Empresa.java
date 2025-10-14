package mack.ps2.estagios.estagios.model;

public class Empresa {
    private String nome;
    private String cnpj;
    private String email;
    private long ID;

    public Empresa(String nome, String cnpj, String email, long ID) {
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
    public long getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
}
