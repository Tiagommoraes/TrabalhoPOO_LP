public class Dinheiro extends FormaPagamento{
    @Override
    public void ProcessarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + "no dinheiro.");
    }
}
