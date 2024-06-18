import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin extends JFrame {

    //Instâncias do Estoque e do Caerinho.
    private final Estoque estoque;
    private final Carrinho carrinho;

    //Cosntrutor.
    public TelaLogin(Estoque estoque, Carrinho carrinho) {
        this.estoque = estoque;
        this.carrinho = carrinho;
        initUI();
    }

    //Método da interface Gráfica.
    private void initUI() {
        setTitle("Supermercado - Login"); //Título da Janela.
        setSize(300, 150); //Tamanho da Janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Fecha a Janela
        setLocationRelativeTo(null);//Centraliza a Janela.

        //Painel Principal com Layout de Borda e Espaçamento.
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Painel e entrada com Layout de grade para os campos do texto.
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel lblUsuario = new JLabel("Usuário:"); //Campo Usuário
        JLabel lblSenha = new JLabel("Senha:"); //Campo Senha.
        JTextField txtUsuario = new JTextField(); //Campo de texto para o Usuário.
        JPasswordField txtSenha = new JPasswordField(); //Campo de texto para Senha.
        inputPanel.add(lblUsuario);
        inputPanel.add(txtUsuario);
        inputPanel.add(lblSenha);
        inputPanel.add(txtSenha);

        //Painel de Botões com Layout de fluxo à Direita.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogin = new JButton("Login");
        JButton btnCancelar = new JButton("Cancelar");

        //Dicas para os Botões (ToolTips)
        btnLogin.setToolTipText("Clique para fazer login");
        btnCancelar.setToolTipText("Clique para cancelar");

        //Botão de Login.
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Verifica se o USUÁRIO  e SENHA estão corretos.
                if (txtUsuario.getText().equals("Admin") && new String(txtSenha.getPassword()).equals("1234")) {
                    carregarProdutosEVitrine(); //Carrega os Produtos e Exibe a Tela Vitrinhe.
                } else {
                    //Menssagem de erro se os DADOS estiverem incorretos.
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Botão Cancelar.
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //Fecha a aplicação.
            }
        });

        //Botões ao painel de Botões.
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnLogin);

        // Adiciona os painéis de entrada e de botões ao painel principal.
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        //Adiciona o painel principal à Janela.
        add(panel);
        // Define o botão de login como o botão padrão para o painel raiz
        getRootPane().setDefaultButton(btnLogin);
        //Ajusta o tamanho da janela aos componentes.
        pack();
        //Torna a Janela vísivel.
        setVisible(true);
    }

    //Método para carregar os produtos e exibir na vitrine.
    private void carregarProdutosEVitrine() {
        // Obtém a lista de produtos a partir da conexão SQLite.
        List<Produto> produtos = SQLiteConnection.getProdutos();
        // Exibe os produtos no estoque.
        estoque.exibirProdutos(produtos);
        // Abre a tela principal e fecha a tela de login.
        new TelaPrincipal(estoque, carrinho).setVisible(true);
        dispose();
    }
}
