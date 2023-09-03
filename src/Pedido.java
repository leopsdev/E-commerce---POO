import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private Cliente cliente;        // Ideia: Já que tem cliente, põe pedido. Todo pedido é feito por um cliente a um vendedor.
    private LocalDateTime data;
    private CarrinhoDeCompras carrinho;     // Ideia: Se eu já recebo o cliente e cada cliente tem um carrinho, é necessário receber o carrinho?
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

    public void mostrarPedido(){
        for(Produto produto: this.carrinho.getProdutos()){
            System.out.printf("%d - %s -------------------- %lf\n", produto.getId_produto(), produto.getNome(), produto.getPreco());
        }
        System.out.printf("Total: %lf", getTotalPedido());
    }

    public void concluirPedido(){
        if(status == StatusPedido.PROCESSANDO){
            status = StatusPedido.REALIZADO;
            this.cliente.adicionarAoHistorico(this);
        }
    }

    public void cancelarPedido(){
        if(status == StatusPedido.PROCESSANDO){
            status = StatusPedido.CANCELADO;
        }
    }

    public boolean verificacaoCompra(){
        if(this.status == StatusPedido.CONCLUIDO){
            System.out.println("Pedido concluído com sucesso. Seus produtos serão entregues em breve.");
            return true;
        }
        else{
            System.out.println("Seu pedido foi cancelado na hora do pagamento. Até a próxima.");
            return false;
        }
    }
    
    public void mostrarHistorico(){
        System.out.println("=-=-=-=-=-=-=-=-=-=- Lista de Pedidos =-=-=-=-=--=-==-=-=-");
        for (Pedido pedido: this.cliente.getHistoricoCompras()){
            pedido.mostrarPedido();
            System.out.println("\n");
        }
    }
    
}

enum StatusPedido {
    PENDENTE, PROCESSANDO, REALIZADO, CONCLUIDO, ENTREGUE, CANCELADO
}
