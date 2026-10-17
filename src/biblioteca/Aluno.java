package biblioteca;

public class Aluno extends Usuario {

    public Aluno(String nome) {
        super(nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }
}