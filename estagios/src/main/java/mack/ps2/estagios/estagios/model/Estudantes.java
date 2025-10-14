import java.time.LocalDate;

public class Estudantes {

    //ATRIBUTOS
    private long ID;
    private String nome;
    private String email;
    private LocalDate nascimento;
    private Int anoIngresso;

    //MÉTODO CONSTRUTOR

    public Estudante (long ID, String nome, String email, LocalDate dataNascimento, Int anoIngresso){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.anoIngresso = anoIngresso;
    }

    //CONSTRUTOR VAZIO

    public Estudante (){

    }

    //GET E SET

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public LocalDate getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimeto(){
        this.dataNascimento = dataNascimento;
    }

    public Int getAnoIngresso(){
        return anoIngresso = anoIngresso;
    }

    public void setAnoIngresso(){
        this.anoIngresso = anoIngresso;
    }



}