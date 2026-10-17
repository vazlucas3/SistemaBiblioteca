package biblioteca;

public class Biblioteca {

    private static final int CAPACIDADE = 100;

    private ItemBiblioteca[] acervo;
    private Usuario[] responsaveis;
    private int quantidadeItens;

    public Biblioteca() {
        acervo = new ItemBiblioteca[CAPACIDADE];
        responsaveis = new Usuario[CAPACIDADE];
        quantidadeItens = 0;
    }

    public boolean cadastrarItem(ItemBiblioteca item) {
        if (item == null || quantidadeItens >= CAPACIDADE) {
            System.out.println("Não foi possível cadastrar o item.");
            return false;
        }

        for (int i = 0; i < quantidadeItens; i++) {
            if (acervo[i].getCodigo() == item.getCodigo()) {
                System.out.println("Já existe um item com esse código.");
                return false;
            }
        }

        acervo[quantidadeItens] = item;
        responsaveis[quantidadeItens] = null;
        quantidadeItens++;

        System.out.println("Item cadastrado: " + item.getTitulo());
        return true;
    }

    public boolean emprestar(ItemBiblioteca item, Usuario usuario) {
        if (item == null || usuario == null) {
            System.out.println("Item ou usuário inválido.");
            return false;
        }

        int posicao = localizarItem(item);

        if (posicao == -1) {
            System.out.println("Item não encontrado no acervo.");
            return false;
        }

        if (!item.isDisponivel()) {
            System.out.println("Item indisponível: " + item.getTitulo());
            return false;
        }

        if (!usuario.podeEmprestar()) {
            System.out.println("Empréstimo recusado! "
                    + usuario.getNome()
                    + " atingiu o limite de "
                    + usuario.getLimiteEmprestimos()
                    + " itens.");
            return false;
        }

        item.marcarEmprestado();
        responsaveis[posicao] = usuario;
        usuario.registrarEmprestimo();

        System.out.println("Empréstimo realizado: "
                + item.getTitulo()
                + " para " + usuario.getNome()
                + ". Prazo: " + item.getPrazoEmprestimo()
                + " dias.");

        return true;
    }

    public boolean devolver(ItemBiblioteca item, Usuario usuario) {
        if (item == null || usuario == null) {
            System.out.println("Item ou usuário inválido.");
            return false;
        }

        int posicao = localizarItem(item);

        if (posicao == -1) {
            System.out.println("Item não encontrado no acervo.");
            return false;
        }

        if (item.isDisponivel()) {
            System.out.println("Esse item não está emprestado.");
            return false;
        }

        if (responsaveis[posicao] != usuario) {
            System.out.println("Devolução recusada: o item não está "
                    + "emprestado para " + usuario.getNome() + ".");
            return false;
        }

        item.marcarDisponivel();
        responsaveis[posicao] = null;
        usuario.registrarDevolucao();

        System.out.println("Devolução realizada: " + item.getTitulo());
        return true;
    }

    public void listarAcervo() {
        System.out.println("\n===== ACERVO DA BIBLIOTECA =====");

        if (quantidadeItens == 0) {
            System.out.println("Acervo vazio.");
            return;
        }

        for (int i = 0; i < quantidadeItens; i++) {
            ItemBiblioteca item = acervo[i];

            System.out.println(
                    "Código: " + item.getCodigo()
                            + " | Título: " + item.getTitulo()
                            + " | Disponível: "
                            + (item.isDisponivel() ? "Sim" : "Não")
                            + " | Prazo: " + item.getPrazoEmprestimo()
                            + " dias"
            );
        }
    }

    private int localizarItem(ItemBiblioteca item) {
        for (int i = 0; i < quantidadeItens; i++) {
            if (acervo[i] == item) {
                return i;
            }
        }

        return -1;
    }
}