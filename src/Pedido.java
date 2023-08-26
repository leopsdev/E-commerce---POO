import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDateTime data;
    private CarrinhoDeCompras carrinho;
    private StatusPedido status = StatusPedido.PENDENTE;
    private static int proximoID = 1;

    public Pedido(Cliente cliente, CarrinhoDeCompras carrinho) {
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.data = LocalDateTime.now();
        this.id = proximoID;
        proximoID++;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    public LocalDateTime getData() {
        return data;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }
    public void setCarrinho(CarrinhoDeCompras carrinho) {
        this.carrinho = carrinho;
    }
    public double getTotalPedido() {
        return getCarrinho().calculaTotalCompra();
    }

    public void mostrarPedido(Pedido pedido){
        for(Produto produto: pedido.carrinho.getProdutos()){
            System.out.printf("%d - %s -------------------- %lf\n", produto.getId_produto(), produto.getNome(), produto.getPreco());
        }
        System.out.printf("Total: %lf", getTotalPedido());
    }

    public void concluirPedido(){
        if(status == StatusPedido.PROCESSANDO){
            status = StatusPedido.ENTREGUE;
            this.cliente.adicionarAoHistorico(this);
        }
    }

    public void cancelarPedido(){
        if(status == StatusPedido.PROCESSANDO){
            status = StatusPedido.CANCELADO;
        }
    }

    
    public void mostrarHistorico(){
        System.out.println("=-=-=-=-=-=-=-=-=-=- Lista de Pedidos =-=-=-=-=--=-==-=-=-");
        for (Pedido pedido: this.cliente.getHistoricoCompras()){
            mostrarPedido(pedido);
            System.out.println("\n");
        }
    }
    
}

enum StatusPedido {
    PENDENTE, PROCESSANDO, ENTREGUE, CANCELADO
}
