package GradedIndividualTask.Java;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    enum Categoria {
        LANCHE,
        BEBIDA,
        DOCE
    }

    enum TipoPagamento {
        DINHEIRO,
        PIX,
        CARTAO_CREDITO,
        CARTAO_DEBITO
    }

    static class Produto {
        private int codigo;
        private String nome;
        private Categoria categoria;
        private double preco;
        private int quantidadeEstoque;

        public Produto(int codigo, String nome, Categoria categoria,
                       double preco, int quantidadeEstoque) {
            this.codigo = codigo;
            this.nome = nome;
            this.categoria = categoria;
            this.preco = preco;
            this.quantidadeEstoque = quantidadeEstoque;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getNome() {
            return nome;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public double getPreco() {
            return preco;
        }

        public int getQuantidadeEstoque() {
            return quantidadeEstoque;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public void atualizarEstoque(int quantidade) {
            quantidadeEstoque += quantidade;
        }
    }

    static class ItemPedido {
        private Produto produto;
        private int quantidade;

        public ItemPedido(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() {
            return produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double calcularSubtotal() {
            return produto.getPreco() * quantidade;
        }
    }

    static class Pagamento {
        private TipoPagamento tipo;
        private double valorPago;

        public Pagamento(TipoPagamento tipo, double valorPago) {
            this.tipo = tipo;
            this.valorPago = valorPago;
        }

        public TipoPagamento getTipo() {
            return tipo;
        }

        public double getValorPago() {
            return valorPago;
        }

        public boolean pagamentoSuficiente(double total) {
            return valorPago >= total;
        }

        public double calcularTroco(double total) {
            return valorPago - total;
        }
    }

    static class Pedido {
        private List<ItemPedido> itens;
        private Pagamento pagamento;
        private LocalDateTime dataHora;

        public Pedido() {
            itens = new ArrayList<>();
            dataHora = LocalDateTime.now();
        }

        public void adicionarItem(ItemPedido item) {
            if (item.getProduto().getQuantidadeEstoque()
                    >= item.getQuantidade()) {

                itens.add(item);

            } else {
                System.out.println("Estoque insuficiente.");
            }
        }

        public double calcularTotal() {
            double total = 0;

            for (ItemPedido item : itens) {
                total += item.calcularSubtotal();
            }

            return total;
        }

        public void setPagamento(Pagamento pagamento) {
            this.pagamento = pagamento;
        }

        public boolean finalizar() {

            double total = calcularTotal();

            if (itens.isEmpty()) {
                System.out.println("O pedido está vazio.");
                return false;
            }

            if (pagamento == null) {
                System.out.println("Pagamento não informado.");
                return false;
            }

            if (!pagamento.pagamentoSuficiente(total)) {
                System.out.println("Pagamento insuficiente.");
                return false;
            }

            for (ItemPedido item : itens) {
                item.getProduto().atualizarEstoque(
                        -item.getQuantidade()
                );
            }

            return true;
        }

        public List<ItemPedido> getItens() {
            return itens;
        }

        public Pagamento getPagamento() {
            return pagamento;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }
    }

    public static void main(String[] args) {

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

        Pedido pedido = new Pedido();

        pedido.adicionarItem(
                new ItemPedido(coxinha, 2)
        );

        pedido.adicionarItem(
                new ItemPedido(suco, 1)
        );

        double total = pedido.calcularTotal();

        Pagamento pagamento = new Pagamento(
                TipoPagamento.DINHEIRO,
                20.00
        );

        pedido.setPagamento(pagamento);

        boolean finalizado = pedido.finalizar();

        System.out.println("================================");
        System.out.println("       CANTINA ESCOLAR");
        System.out.println("================================");

        System.out.println("Data: " + pedido.getDataHora());

        System.out.println("--------------------------------");

        for (ItemPedido item : pedido.getItens()) {
            System.out.println(
                    item.getQuantidade()
                    + "x "
                    + item.getProduto().getNome()
                    + " - R$ "
                    + item.calcularSubtotal()
            );
        }

        System.out.println("--------------------------------");

        System.out.printf("Total: R$ %.2f%n", total);

        System.out.println(
                "Pagamento: " + pagamento.getTipo()
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
                "Pedido finalizado: " + finalizado
        );

        System.out.println("--------------------------------");

        System.out.println(
                "Estoque da Coxinha: "
                + coxinha.getQuantidadeEstoque()
        );

        System.out.println(
                "Estoque do Suco: "
                + suco.getQuantidadeEstoque()
        );

        System.out.println("================================");
    }
}