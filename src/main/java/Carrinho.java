import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    // Lista de itens de venda no carrinho.
    private List<ItemVenda> itens;

    //Construtor.
    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar um item ao carrinho.
    public void adicionarItem(Produto produto, int quantidade) {
        // Adiciona um novo ItemVenda à lista de itens.
        this.itens.add(new ItemVenda(produto, quantidade));
    }

    // Método para remover um item do carrinho.
    public void removerItem(ItemVenda item) {
        // Remove o item especificado da lista de itens.
        this.itens.remove(item);
    }

    // Método para obter a lista de itens no carrinho.
    public List<ItemVenda> getItens() {
        return itens; // Retorna a lista de itens.
    }

    // Método para calcular o total do carrinho.
    public double calcularTotal() {
        double total = 0.0; // Inicializa o total como 0.0.
        for (ItemVenda item : itens) {
            // Itera sobre a lista de itens.
            total += item.getTotal(); // Soma ao total o valor total de cada item (preço * quantidade).
        }
        return total;// Retorna o total calculado.
    }

    public void limparCarrinho() {
        this.itens.clear();
    }
}
