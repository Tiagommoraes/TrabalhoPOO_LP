public class ItemVenda {
    private Produto produto;
    private int quantidade;

    //Construtor.
    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Métodos getters e setters.
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para calcular e retornar o total deste item de venda (preço do produto * quantidade).
    public double getTotal() {
        return produto.getPreco() * quantidade;
    }
}