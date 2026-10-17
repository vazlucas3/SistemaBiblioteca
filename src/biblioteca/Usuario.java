package biblioteca;

public abstract class Usuario {

    private String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public abstract int getLimiteEmprestimos();

    public boolean podeEmprestar() {
        return quantidadeEmprestada < getLimiteEmprestimos();
    }

    void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }
}