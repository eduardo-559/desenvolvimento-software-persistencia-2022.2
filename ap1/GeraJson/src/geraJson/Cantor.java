package geraJson;

public class Cantor {
    private String nome;
    private String ritmo;
    private String cidade;
    private String estado;
    private int idade;

    public Cantor(String nome, String ritmo, String cidade, String estado, int idade) {
        this.nome = nome;
        this.ritmo = ritmo;
        this.cidade = cidade;
        this.estado = estado;
        this.idade = idade;
    }
    
    public Cantor() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Cantor {" + "nome = " + nome + ", ritmo = " + ritmo + ", cidade = " + cidade + ", estado = " + estado + ", idade = " + idade + '}';
    }
}