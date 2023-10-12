package model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;
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

    public double aplicarDesconto(){
        double total = aplicarFrete();
        double desconto;
        if (total>100&&total<250) {
            desconto = (total *10)/100;
            return total - desconto;
        }
        if (total>=250&&total<300) {
            desconto = (total*15)/100;
            return total - desconto;
        }
        if (total>=300&&total<500) {
            desconto = (total*13)/100;
            return total - desconto;
        }
        return total;
    }
    public double aplicarFrete(){
        double total = calculaTotalCompra();
        if (cliente.temHistorico()) {
            total += (Math.random()*100);
        }
        return total;
    }
    
}

enum StatusPedido {
    PENDENTE, REALIZADO, CONCLUIDO, ENTREGUE, CANCELADO
}
