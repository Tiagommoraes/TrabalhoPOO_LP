import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCarrinho extends JFrame {
    private final Carrinho carrinho;

    //Construtor
    public TelaCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
        initUI(); //Inicializando a interface gráfica.
    }

    //Método para inicializar a Interface Gráfica.
    private void initUI() {
        setTitle("Supermercado - Carrinho"); // Título da Janela.
        setSize(800, 600); //Tamanho da Janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Fechamento da Janela.
        setLocationRelativeTo(null); //Centraliza a Janela.

        //Painel Pricipal, Borda e Espaçamento.
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Área de texto para exibição dos itens do carrinho.
        JTextArea txtCarrinho = new JTextArea();
        txtCarrinho.setBorder(BorderFactory.createTitledBorder("Itens no Carrinho")); //Borda no título.
        txtCarrinho.setFont(new Font("Monospaced", Font.PLAIN, 14)); //Fonte.
        txtCarrinho.setEditable(false); //Torna a área do texto não editável.

        //Criando Botões.
        JButton btnPagamento = new JButton("Ir para Pagamento");
        JButton btnVoltar = new JButton("Voltar à Vitrine");
        JButton btnRemover = new JButton("Remover Item");
        JButton btnAlterarQuantidade = new JButton("Alterar Quantidade");

        //Atualiza a área de texto com os itens do carrinho
        atualizarCarrinho(txtCarrinho);

        //Botão de Pagamento
        btnPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre a Tela Pagamento e fecha a Tela Atual.
                new TelaPagamento(carrinho).setVisible(true);
                dispose();
            }
        });

        //Botão Voltar à Vitrine
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre a Tela Principal e fecha a Tela atual.
                new TelaPrincipal(new Estoque(), carrinho).setVisible(true);
                dispose();
            }
        });

        //Botão Remover.
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Solicita o nome do produto para ser removido.
                String produtoNome = JOptionPane.showInputDialog("Informe o nome do produto a ser removido:");
                //Busca o item no carrinho.
                ItemVenda itemParaRemover = carrinho.getItens().stream()
                        .filter(item -> item.getProduto().getNome().equals(produtoNome))
                        .findFirst()
                        .orElse(null);
                if (itemParaRemover != null) {
                    //Remove o item do Carrinho e atualiza a área do texto.
                    carrinho.removerItem(itemParaRemover);
                    JOptionPane.showMessageDialog(null, "Item removido do carrinho.");
                    atualizarCarrinho(txtCarrinho);
                } else {
                    //Menssagem de erro se o produto não for encontrado.
                    JOptionPane.showMessageDialog(null, "Produto não encontrado no carrinho.");
                }
            }
        });

        //Botão Alterar Quantidade.
        btnAlterarQuantidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Solicita o nome do Produto e a nova Quantidade.
                String produtoNome = JOptionPane.showInputDialog("Informe o nome do produto:");
                ItemVenda itemParaAlterar = carrinho.getItens().stream()
                        .filter(item -> item.getProduto().getNome().equals(produtoNome))
                        .findFirst()
                        .orElse(null);
                if (itemParaAlterar != null) {
                    String quantidadeStr = JOptionPane.showInputDialog("Informe a nova quantidade:");
                    try {
                        int novaQuantidade = Integer.parseInt(quantidadeStr);
                        if (novaQuantidade > 0) {
                            //Atualiza a Quantidade e a Área de texto.
                            itemParaAlterar.setQuantidade(novaQuantidade);
                            JOptionPane.showMessageDialog(null, "Quantidade alterada.");
                            atualizarCarrinho(txtCarrinho);
                        } else {
                            //Menssagem de erro se a quantidade for inválida.
                            JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                        }
                    } catch (NumberFormatException ex) {
                        //Menssagem de erro se a quantidade não for um número válido.
                        JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                    }
                } else {
                    //Menssagem de erro se o produto não for localizado.
                    JOptionPane.showMessageDialog(null, "Produto não encontrado no carrinho.");
                }
            }
        });

        //Painel para os Botões com layout horizontal.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        //Botões com espaçamento no painel.
        buttonsPanel.add(btnVoltar);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(btnPagamento);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(btnRemover);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(btnAlterarQuantidade);

        // Adiciona a área de texto e o painel de botões ao painel principal
        panel.add(new JScrollPane(txtCarrinho), BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        //Adiciona o painel principal à janela.
        add(panel);
    }

    //Este método atualiza à área de texto com os itens do carrinho.
    private void atualizarCarrinho(JTextArea txtCarrinho) {
        //Usei o StringBuilder para o texto a ser exibido.
        StringBuilder carrinhoTexto = new StringBuilder();
        for (ItemVenda item : carrinho.getItens()) {
            //Detalhes de cada item ao texto.
            carrinhoTexto.append(String.format("%-20s %-10d R$ %-10.2f\n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getProduto().getPreco() * item.getQuantidade()));
        }
        //Total do carrinho.
        carrinhoTexto.append("\nTotal: R$ ").append(carrinho.calcularTotal());
        //Define o texto.
        txtCarrinho.setText(carrinhoTexto.toString());
    }
}
