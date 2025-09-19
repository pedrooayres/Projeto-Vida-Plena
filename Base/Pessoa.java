package Base;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String contato;

    public Pessoa(String nome, String cpf, String contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getContato() { return contato; }

    @Override
    public String toString() {
        return nome + " | CPF: " + cpf + " | Contato: " + contato;
    }
}
