
package biblioteca;

public class DVD extends ItemBiblioteca {

    public DVD(int codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoEmprestimo() {
        return 3;
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return Math.max(0, diasAtraso) * 2.00;
    }
}
