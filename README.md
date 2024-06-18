# TrabalhoPOO_LP

### Sistema de Supermercado
***Descrição do Projeto.***

Este projeto é um sistema de supermercado com uma interface gráfica amigável e intuitiva, desenvolvido para facilitar a experiência de compra dos usuários. O sistema inclui funcionalidades como login, exibição de produtos, gerenciamento de carrinho de compras e processamento de pagamento. O objetivo é oferecer uma solução completa e eficiente para a operação de um supermercado, desde a seleção de produtos até a conclusão da compra.

***Objetivo do Projeto.***

O objetivo deste projeto é desenvolver um sistema de supermercado que permita aos clientes realizar compras de forma prática e segura, oferecendo diversas opções de pagamento e uma interface gráfica de fácil navegação. O sistema deve ser capaz de gerenciar o estoque de produtos, processar pagamentos e fornecer um fluxo de compra intuitivo para os usuários.

***Justificativa.***

Com o crescimento do comércio eletrônico e a necessidade de proporcionar experiências de compra mais convenientes, este sistema de supermercado visa atender às demandas dos consumidores modernos. A implementação de um sistema completo e integrado não apenas melhora a eficiência operacional, mas também aumenta a satisfação do cliente ao oferecer uma plataforma fácil de usar e múltiplas opções de pagamento.

***Interface Gráfica com o Usuário.***

1. **Tela de Login.**

   A Tela de Login permite que os usuários acessem suas contas para gerenciar suas compras e visualizar o histórico de pedidos. As funcionalidades principais incluem:
   Campos para inserção de Usuário e senha.
   Botão para realizar o login e para cancelar.

<div style="display: flex; justify-content: space-around;" align="center">

   <img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura%20de%20tela%202024-06-10%20191629.png"  />
   
   <img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura%20de%20tela%202024-06-10%20191653.png"  />
</div>

2. **Tela Vitrine**
   
   A Tela de Vitrine é a página principal onde os produtos disponíveis são exibidos com seus preços. Os clientes podem escolher os produtos e adicioná-los ao carrinho de compras. As funcionalidades principais incluem:
Botão para adicionar produtos ao carrinho de compras, botão para ir para o carrinho onde será efetuado o pagamento, e o botão de sair.

<p align="center">
  <img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura%20de%20tela%202024-06-15%20204050.png" width="400" />
  <img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura%20de%20tela%202024-06-15%20204137.png" width="400" />
</p>


3. **Tela de Carrinho de Compras**

   A Tela de Carrinho de Compras exibe os produtos que foram adicionados ao carrinho. As funcionalidades principais incluem:
Lista de produtos no carrinho com preços.
Opção para alterar a quantidade de cada item.
Botão para remover itens do carrinho.
Exibição do total da compra.
Botão para proceder ao pagamento.

<p align="center">
<img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura de tela 2024-06-15 220422.png"  width="400"/>
</p>

4. **Tela de Pagamento**

   A Tela de Pagamento permite que os usuários escolham o método de pagamento para concluir a compra. As formas de pagamento disponíveis são:
Cartão de Débito
Cartão de Crédito
Dinheiro
Pix

<p align="center">
<img src="https://github.com/Tiagommoraes/Trabalho-Supermercado/blob/main/imagens/Captura de tela 2024-06-15 204319.png"  width="400"/>
</p>

> Neste projeto, foram utilizados os 4 pilares da programação orientado à objetos, que são eles:
1.**Herança:** Permite que uma classe (subclasse) herde propriedades e métodos de outra classe (superclasse). No sistema, por exemplo, a classe Cliente herda atributos e métodos da classe Pessoa.
2.**Encapsulamento:** O encapsulamento envolve a restrição do acesso direto a alguns componentes, o que é conseguido através de modificadores de acesso (privado, protegido, público). No sistema, os atributos das classes são encapsulados, permitindo acesso apenas através de métodos específicos.
3.**Polimorfismo:** O polimorfismo permite que objetos de diferentes classes sejam tratados através de uma interface comum. No sistema, diferentes formas de pagamento (como Cartão de Crédito, Débito, Dinheiro e Pix) implementam uma interface comum na Forma de Pagamento, permitindo o tratamento uniforme dessas classes.
4.**Abstração:** A abstração envolve a criação de interfaces ou classes abstratas que definem métodos a serem implementados por subclasses. No sistema, a classe Forma de Pagamento pode ser uma classe abstrata que define métodos a serem implementados pelas classes concretas de cada tipo de pagamento.





   










