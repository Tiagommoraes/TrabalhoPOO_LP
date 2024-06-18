import java.util.List;

public class Venda {
    // Campos que representam o cliente, a lista de itens vendidos e a forma de pagamento.
    private Cliente cliente;
    private List<ItemVenda> itens;
    private FormaPagamento formaPagamento;

    //Construtor da Classe.
    public Venda(Cliente cliente, List<ItemVenda> itens, FormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.itens = itens;
        this.formaPagamento = formaPagamento;
    }

    //Método para calcular o valor total.
    public double calcularTotal() {
        double total = 0; //Inicializa o total com 0.
        for (ItemVenda item : itens) {
            // Soma ao total o preço do produto multiplicado pela quantidade vendida.
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total; //Retorna o total calculado.
    }
}
