package GradedIndividualTask.Java;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // ENUM CATEGORIA

    enum Categoria {
        LANCHE,
        BEBIDA,
        DOCE
    }


    // ENUM TIPO DE PAGAMENTO

    enum TipoPagamento {
        DINHEIRO,
        PIX,
        CARTAO_CREDITO,
        CARTAO_DEBITO
    }


    // CLASSE PRODUTO

    static class Produto {
        private int codigo;
        private String nome;
        private Categoria categoria;
        private double preco;
        private int quantidadeEstoque;
        private boolean ativo;

        public Produto(int codigo, String nome, Categoria categoria,
                       double preco, int quantidadeEstoque) {

            this.codigo = codigo;
            this.nome = nome;
            this.categoria = categoria;
            this.preco = preco;
            this.quantidadeEstoque = quantidadeEstoque;
            this.ativo = true;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public int getQuantidadeEstoque() {
            return quantidadeEstoque;
        }

        public boolean isAtivo() {
            return ativo;
        }

        public void alterarPreco(double novoPreco) {
            if (novoPreco < 0) {
                throw new IllegalArgumentException(
                        "O preço não pode ser negativo."
                );
            }

            this.preco = novoPreco;
        }

        public void atualizarEstoque(int quantidade) {
            if (quantidadeEstoque + quantidade < 0) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente."
                );
            }

            quantidadeEstoque += quantidade;
        }

        public boolean possuiEstoque(int quantidade) {
            return ativo && quantidade > 0
                    && quantidadeEstoque >= quantidade;
        }

        public void removerDoCardapio() {
            ativo = false;
        }
    }


    // CLASSE ITEM PEDIDO

    static class ItemPedido {
        private Produto produto;
        private int quantidade;

        public ItemPedido(Produto produto, int quantidade) {

            if (produto == null) {
                throw new IllegalArgumentException(
                        "O produto não pode ser nulo."
                );
            }

            if (quantidade <= 0) {
                throw new IllegalArgumentException(
                        "A quantidade deve ser maior que zero."
                );
            }

            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() {
            return produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            if (quantidade <= 0) {
                throw new IllegalArgumentException(
                        "A quantidade deve ser maior que zero."
                );
            }

            this.quantidade = quantidade;
        }

        public double calcularSubtotal() {
            return produto.getPreco() * quantidade;
        }
    }


    // CLASSE PAGAMENTO

    static class Pagamento {
        private TipoPagamento tipo;
        private double valorPago;

        public Pagamento(TipoPagamento tipo, double valorPago) {

            if (tipo == null) {
                throw new IllegalArgumentException(
                        "Tipo de pagamento inválido."
                );
            }

            if (valorPago < 0) {
                throw new IllegalArgumentException(
                        "O valor não pode ser negativo."
                );
            }

            this.tipo = tipo;
            this.valorPago = valorPago;
        }

        public TipoPagamento getTipo() {
            return tipo;
        }

        public double getValorPago() {
            return valorPago;
        }

        public void setValorPago(double valorPago) {
            if (valorPago < 0) {
                throw new IllegalArgumentException(
                        "O valor não pode ser negativo."
                );
            }

            this.valorPago = valorPago;
        }

        public boolean pagamentoSuficiente(double valorTotal) {
            return valorPago >= valorTotal;
        }

        public double calcularTroco(double valorTotal) {
            if (!pagamentoSuficiente(valorTotal)) {
                return 0;
            }

            return valorPago - valorTotal;
        }
    }


    // CLASSE PEDIDO

    static class Pedido {

        private List<ItemPedido> itens;
        private Pagamento pagamento;
        private LocalDateTime dataHora;
        private boolean finalizado;

        public Pedido() {
            itens = new ArrayList<>();
            dataHora = LocalDateTime.now();
            finalizado = false;
        }

        public void adicionarItem(ItemPedido item) {

            if (finalizado) {
                throw new IllegalStateException(
                        "O pedido já foi finalizado."
                );
            }

            if (!item.getProduto().possuiEstoque(
                    item.getQuantidade())) {

                throw new IllegalArgumentException(
                        "Estoque insuficiente para: "
                                + item.getProduto().getNome()
                );
            }

            itens.add(item);
        }

        public double calcularTotal() {

            double total = 0;

            for (ItemPedido item : itens) {
                total += item.calcularSubtotal();
            }

            return total;
        }

        public void setPagamento(Pagamento pagamento) {

            if (finalizado) {
                throw new IllegalStateException(
                        "O pedido já foi finalizado."
                );
            }

            this.pagamento = pagamento;
        }

        public Pagamento getPagamento() {
            return pagamento;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public boolean isFinalizado() {
            return finalizado;
        }

        public List<ItemPedido> getItens() {
            return itens;
        }

        public boolean finalizar() {

            if (itens.isEmpty()) {
                throw new IllegalStateException(
                        "O pedido precisa ter pelo menos um item."
                );
            }

            if (pagamento == null) {
                throw new IllegalStateException(
                        "O pedido precisa ter um pagamento."
                );
            }

            double total = calcularTotal();

            if (!pagamento.pagamentoSuficiente(total)) {
                throw new IllegalStateException(
                        "Pagamento insuficiente."
                );
            }

            // Confere novamente o estoque
            for (ItemPedido item : itens) {

                if (!item.getProduto().possuiEstoque(
                        item.getQuantidade())) {

                    throw new IllegalStateException(
                            "Estoque insuficiente para: "
                                    + item.getProduto().getNome()
                    );
                }
            }

            // Retira os produtos do estoque
            for (ItemPedido item : itens) {

                item.getProduto().atualizarEstoque(
                        -item.getQuantidade()
                );
            }

            finalizado = true;

            return true;
        }
    }

    // MAIN

    public static void main(String[] args) {

        // Criando produtos
        Produto coxinha = new Produto(
                1,
                "Coxinha",
                Categoria.LANCHE,
                6.50,
                20
        );

        Produto suco = new Produto(
                2,
                "Suco",
                Categoria.BEBIDA,
                5.00,
                15
        );

        Produto brigadeiro = new Produto(
                3,
                "Brigadeiro",
                Categoria.DOCE,
                3.00,
                10
        );

        // Criando pedido
        Pedido pedido = new Pedido();

        // Adicionando produtos
        pedido.adicionarItem(
                new ItemPedido(coxinha, 2)
        );

        pedido.adicionarItem(
                new ItemPedido(suco, 1)
        );

        // Calculando total
        double total = pedido.calcularTotal();

        // Criando pagamento
        Pagamento pagamento = new Pagamento(
                TipoPagamento.DINHEIRO,
                20.00
        );

        // Adicionando pagamento ao pedido
        pedido.setPagamento(pagamento);

        // Finalizando pedido
        pedido.finalizar();

        // =========================
        // EXIBIÇÃO
        // =========================

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                );

        System.out.println();
        System.out.println("======================================");
        System.out.println("          CANTINA ESCOLAR");
        System.out.println("======================================");

        System.out.println(
                "Data: "
                        + pedido.getDataHora().format(formato)
        );

        System.out.println("--------------------------------------");

        for (ItemPedido item : pedido.getItens()) {

            System.out.printf(
                    "%d x %s - R$ %.2f%n",
                    item.getQuantidade(),
                    item.getProduto().getNome(),
                    item.calcularSubtotal()
            );
        }

        System.out.println("--------------------------------------");

        System.out.printf(
                "TOTAL: R$ %.2f%n",
                total
        );

        System.out.println(
                "Pagamento: "
                        + pagamento.getTipo()
        );

        System.out.printf(
                "Valor pago: R$ %.2f%n",
                pagamento.getValorPago()
        );

        System.out.printf(
                "Troco: R$ %.2f%n",
                pagamento.calcularTroco(total)
        );

        System.out.println(
                "Status: "
                        + (pedido.isFinalizado()
                        ? "FINALIZADO"
                        : "EM ABERTO")
        );

        System.out.println("--------------------------------------");

        System.out.println(
                "Estoque Coxinha: "
                        + coxinha.getQuantidadeEstoque()
        );

        System.out.println(
                "Estoque Suco: "
                        + suco.getQuantidadeEstoque()
        );

        System.out.println(
                "Estoque Brigadeiro: "
                        + brigadeiro.getQuantidadeEstoque()
        );

        System.out.println("======================================");
    }
}