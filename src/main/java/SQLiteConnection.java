import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteConnection {

    // URL de conexão com o banco de dados SQLite.
    private static final String URL = "jdbc:sqlite:BD_supermercados.db";

    // Método para conectar ao banco de dados.
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para inicializar o banco de dados, criando a tabela produtos.
    public static void initializeDatabase() {
        String dropTableSQL = "DROP TABLE IF EXISTS produtos";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS produtos (" +
                "nome TEXT PRIMARY KEY, " +
                "preco REAL NOT NULL, " +
                "quantidade INTEGER NOT NULL)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(dropTableSQL);  // Remove a tabela se existir
            stmt.execute(createTableSQL);  // Cria a tabela novamente
            System.out.println("Database initialized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um produto ao banco de dados.
    public static void addProduto(Produto produto) {
        String insertSQL = "INSERT INTO produtos(nome, preco, quantidade) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, produto.getQuantidade());
            pstmt.executeUpdate();
            System.out.println("Produto " + produto.getNome() + " adicionado ao banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter a lista de produtos do banco de dados.
    public static List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT nome, preco, quantidade FROM produtos";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Itera sobre os resultados e cria objetos Produto.
            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                Produto produto = new Produto(nome, preco, quantidade);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;  // Retorna a lista de produtos.
    }

    public static void updateQuantidade(Produto produto, int quantidade) {
        String updateSQL = "UPDATE produtos SET quantidade = ? WHERE nome = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setInt(1, quantidade);
            pstmt.setString(2, produto.getNome());
            pstmt.executeUpdate();
            System.out.println("Quantidade do produto " + produto.getNome() + " atualizada para " + quantidade + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para abrir a tela de login.
    public static void abrirTelaLogin() {
        Estoque estoque = new Estoque(); //Nova instância de Estoque
        Carrinho carrinho = new Carrinho();//Nova instância de Carrinho.
        new TelaLogin(estoque, carrinho).setVisible(true); // Abre a tela de login.
    }
    // Método principal para executar o programa.
    public static void main(String[] args) {
        initializeDatabase(); // Inicializa o banco de dados.

        // Instâncias de produtos.
        Produto produto1 = new Produto("Arroz", 30.0, 10);
        Produto produto2 = new Produto("Feijão", 10.0, 20);
        Produto produto3 = new Produto("Macarrão", 5.0, 30);
        Produto produto4 = new Produto("Azeite", 50.0, 15);
        Produto produto5 = new Produto("Bis", 10.0, 25);

        // Adiciona os produtos ao banco de dados.
        addProduto(produto1);
        addProduto(produto2);
        addProduto(produto3);
        addProduto(produto4);
        addProduto(produto5);

        abrirTelaLogin(); // Abre a tela de login
    }
}
