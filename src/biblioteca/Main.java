package biblioteca;

public class Main {

    public static void main(String[] args) {

        // 1. Criando a biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // 2. Cadastrando os itens
        Livro livro1 = new Livro(1, "Dom Casmurro");
        Livro livro2 = new Livro(2, "O Pequeno Príncipe");
        Revista revista1 = new Revista(3, "Revista de Tecnologia");
        Livro livro3 = new Livro(4, "Java para Iniciantes");

        biblioteca.cadastrarItem(livro1);
        biblioteca.cadastrarItem(livro2);
        biblioteca.cadastrarItem(revista1);
        biblioteca.cadastrarItem(livro3);

        // 3. Cadastrando os usuários
        Aluno aluno = new Aluno("Lucas");
        Professor professor = new Professor("Carlos");

        // 4. Listando o acervo inicial
        biblioteca.listarAcervo();

        // 5. Empréstimos do aluno
        System.out.println("\n===== TESTE DE EMPRÉSTIMOS =====");

        biblioteca.emprestar(livro1, aluno);
        biblioteca.emprestar(livro2, aluno);
        biblioteca.emprestar(revista1, aluno);

        // 6. Tentativa acima do limite do aluno
        System.out.println("\n===== TESTE DE LIMITE =====");

        biblioteca.emprestar(livro3, aluno);

        // 7. Testando empréstimo para professor
        System.out.println("\n===== TESTE DO PROFESSOR =====");

        biblioteca.emprestar(livro3, professor);

        // 8. Testando devolução
        System.out.println("\n===== TESTE DE DEVOLUÇÃO =====");

        biblioteca.devolver(livro1, aluno);

        // 9. Conferindo a multa do livro
        System.out.println("\n===== TESTE DE MULTA =====");

        int diasAtraso = 5;

        System.out.printf(
                "Multa do livro por %d dias de atraso: R$ %.2f%n",
                diasAtraso,
                livro1.calcularMulta(diasAtraso)
        );

        System.out.printf(
                "Multa da revista por %d dias de atraso: R$ %.2f%n",
                diasAtraso,
                revista1.calcularMulta(diasAtraso)
        );

        // 10. Listagem final
        biblioteca.listarAcervo();

        System.out.println("\n===== QUANTIDADE EMPRESTADA =====");

        System.out.println(
                aluno.getNome() + ": "
                        + aluno.getQuantidadeEmprestada() + " item(ns)"
        );

        System.out.println(
                professor.getNome() + ": "
                        + professor.getQuantidadeEmprestada() + " item(ns)"
        );
    }
}