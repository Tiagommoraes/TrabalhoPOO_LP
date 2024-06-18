import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TelaPrincipal extends JFrame {
    //Intâncias Estoque e Carrinho.
    private final Estoque estoque;
    private final Carrinho carrinho;

    //Construtor.
    public TelaPrincipal(Estoque estoque, Carrinho carrinho) {
        this.estoque = estoque;
        this.carrinho = carrinho;
        initUI();
    }

    //Método para iniciar a Interface Gráfica.
    private void initUI() {
        setTitle("Supermercado - Vitrine"); //Título da Janela
        setSize(800, 600); //Tamanho da Janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Fecha a Janela.
        setLocationRelativeTo(null); //Centraliza a Janela.

        //Painel Principal com Layout de Borda e Espaçamento.
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Painel e entrada com Layout de grade para os campos do texto.
        JPanel productListPanel = new JPanel(new BorderLayout(5, 5));
        productListPanel.setBorder(BorderFactory.createTitledBorder("Produtos Disponíveis"));

        //// Obtém os produtos do estoque.
        Map<Produto, Integer> produtos = estoque.getProdutos();
        //Cria um modelo de lista para exibir os produtos.
        DefaultListModel<String> model = new DefaultListModel<>();
        produtos.forEach((produto, quantidade) -> model.addElement(
                produto.getNome() + " - R$ " + produto.getPreco() + " (Estoque: " + quantidade + ")"
        ));
        //Cria uma lista.
        JList<String> listaProdutos = new JList<>(model);
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productListPanel.add(new JScrollPane(listaProdutos), BorderLayout.CENTER);

        // Cria um painel para os botões de ação com layout vertical
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        //Botões de ações.
        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        JButton btnCarrinho = new JButton("Ir para o Carrinho");
        JButton btnSair = new JButton("Sair");

        // Adiciona os botões ao painel de botões com espaçamento vertical.
        buttonsPanel.add(btnAdicionar);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnCarrinho);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(btnSair);

        //Painel para adicionar a quantidade de produtos com layout de fluxo à esquerda
        JPanel addQuantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addQuantityPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Produto"));

        //Campo de texto para a quantidade.
        JLabel labelQuantidade = new JLabel("Quantidade:");
        JTextField fieldQuantidade = new JTextField(5);
        addQuantityPanel.add(labelQuantidade);
        addQuantityPanel.add(fieldQuantidade);

        //Botão Adicionar.
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o produto selecionado e a quantidade informada.
                String selectedValue = listaProdutos.getSelectedValue();
                if (selectedValue != null && !fieldQuantidade.getText().isEmpty()) {
                    String produtoNome = selectedValue.split(" - ")[0];
                    Produto produto = produtos.keySet().stream()
                            .filter(p -> p.getNome().equals(produtoNome))
                            .findFirst()
                            .orElse(null);
                    if (produto != null) {
                        try {
                            int quantidade = Integer.parseInt(fieldQuantidade.getText());
                            // Verifica se a quantidade é válida e se há estoque suficiente.
                            if (quantidade > 0 && quantidade <= estoque.consultarEstoque(produto)) {
                                carrinho.adicionarItem(produto, quantidade);
                                estoque.removerProduto(produto, quantidade);
                                JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho!");
                                updateProductList(model, produtos); // Atualiza a lista de produtos.
                            } else {
                                JOptionPane.showMessageDialog(null, "Quantidade inválida ou insuficiente no estoque.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um produto válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto e informe a quantidade.");
                }
            }
        });

        //Botão Carrinho.
        btnCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCarrinho(carrinho).setVisible(true);
                dispose();
            }
        });

        //Botão Sair.
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Adiciona os painéis ao painel principal.
        panel.add(productListPanel, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.EAST);
        panel.add(addQuantityPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela.
        add(panel);
        setVisible(true); //Torna vísivel a Janela.
    }

    // Método para atualizar a lista de produtos.
    private void updateProductList(DefaultListModel<String> model, Map<Produto, Integer> produtos) {
        model.clear(); //Limpa o modelo da lista.
        //Adiciona os produtos ao modelo da lista.
        produtos.forEach((produto, quantidade) -> model.addElement(
                produto.getNome() + " - R$ " + produto.getPreco() + " (Estoque: " + quantidade + ")"
        ));
    }
}
