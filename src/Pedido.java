import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;        // Ideia: Já que tem cliente, põe pedido. Todo pedido é feito por um cliente a um vendedor.
    private LocalDateTime data;
    private StatusPedido status = StatusPedido.PENDENTE;
    private static int proximoID = 1;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
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
    public double getTotalPedido() {
        return calculaTotalCompra();
    }
    public double calculaTotalCompra(){
        ArrayList<Produto> produtos = cliente.getCarrinhoDeCompras().getProdutos();
        double total = 0;
        for(Produto produto: produtos){
            total += produto.getPreco();
        }
        boolean temHistorico = cliente.temHistorico();
        total = cliente.getCarrinhoDeCompras().aplicarDesconto(total);
        total = cliente.getCarrinhoDeCompras().aplicarFrete(total, temHistorico);
        return total;
    }

    public void mostrarPedido(){
        for(Produto produto: this.cliente.getCarrinhoDeCompras().getProdutos()){
            System.out.println(produto.getId_produto()+ " - "+ produto.getNome() + "-------------------- " + produto.getPreco() + "\n");
        }
        System.out.println("Total: " + getTotalPedido());
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
    PENDENTE, REALIZADO, CONCLUIDO, ENTREGUE, CANCELADO
}
