import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPagamento extends JFrame {
    //Instância.
    private final Carrinho carrinho;

    //Construtor.
    public TelaPagamento(Carrinho carrinho) {
        this.carrinho = carrinho;
        initUI();
    }

    //Método da interface Gráfica.
    private void initUI() {
        setTitle("Supermercado - Pagamento"); //Título da Janela.
        setSize(400, 300); //Tamanho da Janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Fechamento da Janela.
        setLocationRelativeTo(null); //Centraliza a Janela.

        //Painel Principal com Layout de Borda e Espaçamento.
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Painel e entrada com Layout de grade para os campos do texto.
        JLabel lblTotal = new JLabel("Total: R$ " + carrinho.calcularTotal());
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER); //Alinha o texto ao centro.

        //Painel para os métodos de pagamento com layout de grade.
        JPanel paymentMethodsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        paymentMethodsPanel.setBorder(BorderFactory.createTitledBorder("Escolha a forma de pagamento"));

        //Botões para os métodos de pagamentos.
        JRadioButton rbCartaoCredito = new JRadioButton("Cartão de Crédito");
        JRadioButton rbCartaoDebito = new JRadioButton("Cartão de Débito");
        JRadioButton rbPix = new JRadioButton("PIX");
        JRadioButton rbDinheiro = new JRadioButton("Dinheiro");

        //Agrupando os Botões para que apenas um possa ser selecionado por vez.
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(rbCartaoCredito);
        paymentGroup.add(rbCartaoDebito);
        paymentGroup.add(rbPix);
        paymentGroup.add(rbDinheiro);

        // Adiciona os botões de opção ao painel de métodos de pagamento.
        paymentMethodsPanel.add(rbCartaoCredito);
        paymentMethodsPanel.add(rbCartaoDebito);
        paymentMethodsPanel.add(rbPix);
        paymentMethodsPanel.add(rbDinheiro);

        //Painel de Botões com Layout de fluxo à Direita.
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnPagar = new JButton("Pagar");
        JButton btnCancelar = new JButton("Cancelar");

        //Botão de Pagar.
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verifica se foi selecionado alguma opção de pagamento.
                if (!rbCartaoCredito.isSelected() && !rbCartaoDebito.isSelected() &&
                        !rbPix.isSelected() && !rbDinheiro.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Selecione uma forma de pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Exibe uma mensagem de sucesso e abre a tela principal, fechando a tela de pagamento.
                JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
                new TelaPrincipal(new Estoque(), carrinho).setVisible(true);
                dispose();
            }
        });

        //Botão Cancelar.
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre a Tela principal e fecha a Tela Pagamento.
                new TelaPrincipal(new Estoque(), carrinho).setVisible(true);
                dispose();
            }
        });

        // Adiciona os botões ao painel de botões.
        buttonsPanel.add(btnCancelar);
        buttonsPanel.add(btnPagar);

        // Adiciona os componentes ao painel principal.
        panel.add(lblTotal, BorderLayout.NORTH);
        panel.add(paymentMethodsPanel, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela.
        add(panel);
    }
}
