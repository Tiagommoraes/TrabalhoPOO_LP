public class CartaoCredito extends FormaPagamento {
    @Override
    public void ProcessarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " no cartão de crédito.");
    }
}