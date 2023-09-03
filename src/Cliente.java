import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Usuario{
    private long cpf;
    private String info_pagamento;
    private List<Produto> listaDesejos = new ArrayList<>();
    private List<Pedido> historicoCompras = new ArrayList<>();
    private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

    Scanner scan = new Scanner(System.in);

    public Cliente(String nome, String endereco, String email, long cpf, String info_pagamento) {
        super(nome, endereco, email);
        this.info_pagamento = info_pagamento;
        this.cpf = cpf;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public String getInfo_pagamento() {
        return info_pagamento;
    }
    public void setInfo_pagamento(String info_pagamento) {
        this.info_pagamento = info_pagamento;
    }
    public List<Produto> getListaDesejos() {
        return listaDesejos;
    }
    public List<Pedido> getHistoricoCompras() {
        return historicoCompras;
    }
    public CarrinhoDeCompras getCarrinhoDeCompras(){
        return this.carrinho;
    }

    public void adicionarAoCarrinho(Produto produto){
        System.out.println("Digite quantas unidades deseja comprar: ");
        int quantidade = scan.nextInt();
        this.carrinho.adicionarProduto(produto, quantidade);
        
    }
    public Pedido fazerPedido(){
        Pedido pedido = new Pedido(this, carrinho);
        //pedido.mostrarPedido(pedido);
        return pedido;
    }
    public void realizarCompra(Pagamento pagamento, Vendedor vendedor){
        Pedido pedido = fazerPedido();
        vendedor.adicionarListaDePedido(pedido);    
        Pedido pedido_processado = vendedor.processarPedido(pedido);
        Pedido pedido_pago = pagamento.processarPagamento(pedido_processado);
        
        // Quando o cliente for fazer o pagamento, pergunto quais os dados do pagamento desse cliente pra poder criar a variável do tipo Pagam.
        
        boolean verificacao = pedido_pago.verificacaoCompra();
        if(verificacao){
            historicoCompras.add(pedido);
            carrinho.limparCarrinho();
            vendedor.realizaVenda(pedido_pago);
        }
        else{
            System.out.println("Compra não realizada.");            
        } 
    }

    public void adicionarListaDeDesejos(Produto produto){
        this.listaDesejos.add(produto);
    }

    public void adicionarAoHistorico(Pedido pedido){
        this.historicoCompras.add(pedido);
    }
    public boolean temHistorico(){
        return !historicoCompras.isEmpty();
    }
    
}
