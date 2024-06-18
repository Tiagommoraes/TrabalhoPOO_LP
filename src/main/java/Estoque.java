import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtos;

    //Construtor.
    public Estoque() {
        this.produtos = new HashMap<>();
        loadProdutosFromDatabase();
    }

    // Método privado para carregar os produtos do banco de dados ao inicializar o estoque.
    private void loadProdutosFromDatabase() {
        List<Produto> produtosList = SQLiteConnection.getProdutos();
        for (Produto produto : produtosList) {
            this.produtos.put(produto, produto.getQuantidade());
        }
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        // Adiciona um produto ao estoque com a quantidade especificada.
        this.produtos.put(produto, this.produtos.getOrDefault(produto, 0) + quantidade);
        // Atualiza a quantidade do produto no banco de dados.
        SQLiteConnection.updateQuantidade(produto, this.produtos.get(produto));
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (this.produtos.containsKey(produto)) {
            int novaQuantidade = this.produtos.get(produto) - quantidade;
            if (novaQuantidade <= 0) {
                // Remove o produto do estoque se a quantidade ficar menor ou igual a zero.
                this.produtos.remove(produto);
                // Atualiza a quantidade do produto para zero no banco de dados.
                SQLiteConnection.updateQuantidade(produto, 0);
            } else {
                // Atualiza a quantidade restante do produto no estoque.
                this.produtos.put(produto, novaQuantidade);
                // Atualiza a quantidade do produto no banco de dados.
                SQLiteConnection.updateQuantidade(produto, novaQuantidade);
            }
        }
    }

    // Consulta a quantidade disponível de um produto no estoque.
    public int consultarEstoque(Produto produto) {
        return this.produtos.getOrDefault(produto, 0);
    }

    public Map<Produto, Integer> getProdutos() {
        // Retorna uma cópia do mapa de produtos para proteger os dados originais
        return new HashMap<>(this.produtos);
    }

    // Método para exibir os produtos, poderia ser implementado se necessário.
    public void exibirProdutos(List<Produto> produtos) {

    }
}