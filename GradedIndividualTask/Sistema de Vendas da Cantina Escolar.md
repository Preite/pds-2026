# 1. Tarefa 1 – Casos de Uso

## 1.1 Atores

Para o funcionamento do sistema foram identificados três atores principais. Cada um possui funções diferentes dentro da cantina.

* **Cliente:** pode consultar o cardápio, escolher os produtos, informar as quantidades, fechar o pedido e realizar o pagamento.
* **Atendente:** fica responsável por registrar a venda, receber o pagamento e emitir um comprovante para o cliente.
* **Gerente:** pode cadastrar novos produtos, alterar preços, atualizar o estoque, remover produtos que não são mais vendidos e consultar as vendas realizadas.

## 1.2 Principais Casos de Uso

### Cliente

* Consultar cardápio;
* Escolher produtos;
* Informar quantidade;
* Fechar pedido;
* Realizar pagamento.

### Atendente

* Registrar venda;
* Receber pagamento;
* Emitir comprovante.

### Gerente

* Cadastrar produto;
* Alterar preço;
* Atualizar estoque;
* Remover produto do cardápio;
* Consultar vendas realizadas.

---

## 1.3 Caso de Uso – Realizar Venda

### Objetivo

O objetivo é permitir que o cliente faça um pedido com os produtos que deseja e consiga realizar o pagamento da compra.

### Atores envolvidos

* Cliente;
* Atendente.

### Fluxo principal

1. Primeiro, o cliente consulta os produtos disponíveis no cardápio.
2. Depois, escolhe os produtos que deseja comprar.
3. O cliente informa a quantidade de cada produto.
4. O sistema verifica se existe estoque suficiente.
5. Caso esteja disponível, o produto é colocado no pedido.
6. O sistema calcula o subtotal de cada item.
7. Em seguida, o valor total do pedido é calculado.
8. O cliente informa a forma de pagamento.
9. O atendente registra o pagamento recebido.
10. O sistema verifica se o valor pago é suficiente.
11. Com o pagamento correto, o pedido pode ser finalizado.
12. O estoque dos produtos vendidos é atualizado.
13. A data e a hora da venda são registradas.
14. Por fim, o atendente pode emitir o comprovante da venda.

### Fluxos alternativos

**A1 – Produto sem estoque**

1. Ao informar a quantidade, o sistema verifica o estoque.
2. Se não houver quantidade suficiente, o produto não é adicionado.
3. O sistema informa ao cliente que o estoque é insuficiente.
4. O cliente pode escolher outro produto ou diminuir a quantidade.

**A2 – Pagamento insuficiente**

1. O sistema compara o valor pago com o total do pedido.
2. Se o valor for menor, o pagamento não é suficiente.
3. O pedido continua em aberto.
4. O cliente pode complementar o pagamento ou informar outro valor.

---

## 1.4 Caso de Uso – Gerenciar Produtos

### Objetivo

Esse caso de uso permite que o gerente faça alterações nos produtos da cantina e mantenha as informações do estoque atualizadas.

### Ator envolvido

* Gerente.

### Fluxo principal

1. O gerente acessa a parte de gerenciamento dos produtos.
2. Escolhe qual operação deseja realizar.
3. Para cadastrar um produto, informa código, nome, categoria, preço e quantidade em estoque.
4. O sistema verifica os dados informados e realiza o cadastro.
5. Quando necessário, o gerente pode alterar o preço de um produto.
6. Também é possível adicionar ou retirar produtos do estoque.
7. Caso um produto não seja mais vendido, o gerente pode removê-lo do cardápio.
8. Após a operação, o sistema atualiza os dados do produto.

### Fluxos alternativos

**A1 – Código já cadastrado**

1. O gerente informa o código de um novo produto.
2. O sistema verifica que esse código já está sendo utilizado.
3. O cadastro não é concluído.
4. O gerente precisa informar outro código.

**A2 – Estoque insuficiente**

1. O gerente solicita uma retirada de produtos do estoque.
2. O sistema verifica a quantidade disponível.
3. Se a quantidade solicitada for maior que o estoque, a operação não é realizada.
4. O sistema informa que não existe quantidade suficiente.

---

# 2. Tarefa 2 – Diagrama de Classes

## 2.1 Classes

A partir dos requisitos do sistema, foram definidas as classes principais responsáveis pelo funcionamento da venda.

### Produto

A classe `Produto` representa os produtos disponíveis para venda na cantina.

**Atributos:**

* `codigo`
* `nome`
* `categoria`
* `preco`
* `quantidadeEstoque`
* `ativo`

**Principais métodos:**

* `alterarPreco()`
* `atualizarEstoque()`
* `possuiEstoque()`
* `removerDoCardapio()`

---

### ItemPedido

A classe `ItemPedido` representa um produto que foi escolhido para fazer parte de um pedido.

**Atributos:**

* `produto`
* `quantidade`

O subtotal é calculado usando o preço do produto multiplicado pela quantidade escolhida.

**Principal método:**

* `calcularSubtotal()`

---

### Pedido

A classe `Pedido` representa a compra que está sendo realizada pelo cliente.

Ela possui uma lista com os itens escolhidos, além das informações do pagamento e da data da venda.

**Atributos:**

* `itens`
* `pagamento`
* `dataHora`
* `finalizado`

**Principais métodos:**

* `adicionarItem()`
* `calcularTotal()`
* `finalizar()`

---

### Pagamento

A classe `Pagamento` é usada para guardar as informações relacionadas à forma de pagamento utilizada pelo cliente.

O cálculo do valor total continua sendo responsabilidade do `Pedido`. O `Pagamento` apenas verifica se o valor recebido é suficiente e, no caso de dinheiro, pode calcular o troco.

**Atributos:**

* `tipo`
* `valorPago`

**Principais métodos:**

* `pagamentoSuficiente()`
* `calcularTroco()`

---

### Categoria

Foi criado um `enum` chamado `Categoria` para definir os tipos de produtos existentes na cantina.

Os valores utilizados são:

* `LANCHE`
* `BEBIDA`
* `DOCE`

---

### TipoPagamento

Também foi criado um `enum` para representar as formas de pagamento aceitas pelo sistema.

Os valores são:

* `DINHEIRO`
* `PIX`
* `CARTAO_CREDITO`
* `CARTAO_DEBITO`

---

## 2.2 Relacionamentos

### Pedido e ItemPedido

Um `Pedido` possui um ou mais `ItemPedido`.

Nesse caso foi utilizado um relacionamento de **composição**, pois os itens fazem parte do pedido.

**Multiplicidade:**

`Pedido 1 ─── 1..* ItemPedido`

Isso significa que um pedido possui pelo menos um item.

---

### ItemPedido e Produto

Cada `ItemPedido` está relacionado a um único `Produto`.

Um mesmo produto pode aparecer em vários pedidos diferentes, por isso essa relação não é uma composição.

**Relacionamento:** associação.

**Multiplicidade:**

`ItemPedido * ─── 1 Produto`

---

### Pedido e Pagamento

Um pedido pode estar sem pagamento enquanto ainda estiver em aberto. Depois que o cliente realiza o pagamento, o pedido passa a ter um pagamento associado.

**Multiplicidade:**

`Pedido 1 ─── 0..1 Pagamento`

---

### Produto e Categoria

Cada produto possui uma categoria. A categoria é definida pelo `enum Categoria`.

Exemplos:

* Coxinha → `LANCHE`
* Suco → `BEBIDA`
* Brigadeiro → `DOCE`

---

### Pagamento e TipoPagamento

Cada pagamento possui um tipo definido pelo `enum TipoPagamento`.

Por exemplo, o pagamento pode ser feito em dinheiro, PIX, cartão de crédito ou cartão de débito.

---

## 2.3 Resumo do modelo

O funcionamento principal do sistema pode ser resumido da seguinte forma:

`Produto` representa o que a cantina vende.

`ItemPedido` representa a quantidade de um determinado produto dentro da compra.

`Pedido` reúne os itens e calcula o valor total da venda.

`Pagamento` registra como e quanto foi pago.

Dessa forma, cada classe fica responsável por uma parte do sistema, sem colocar todas as funções em uma única classe.
