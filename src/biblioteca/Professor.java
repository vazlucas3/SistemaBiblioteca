package biblioteca;

public class Professor extends Usuario {

    public Professor(String nome) {
        super(nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 5;
    }
}