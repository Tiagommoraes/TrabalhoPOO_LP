public class Pix extends FormaPagamento{
    // Sobrescrevendo o método abstrato ProcessarPagamento da classe pai.
    @Override
    public void ProcessarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + "no pix.");
    }
}
