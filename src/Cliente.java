import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Usuario{
    private long cpf;
    private List<Produto> listaDesejos = new ArrayList<>();
    private List<Pedido> historicoCompras = new ArrayList<>();
    private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
    Scanner scan = new Scanner(System.in);
    public Cliente(String nome, String endereco, String email, String senha, long cpf) {
        super(nome, endereco, email, senha);
        this.cpf = cpf;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
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

    public GerenciadorDePedidos fazerPedido(){
        Pedido pedido = new Pedido(this);
        GerenciadorDePedidos ger_pedido = new GerenciadorDePedidos(pedido);
        ger_pedido.organizaPedidos();
        pedido.mostrarPedido();

        return ger_pedido;
    }
    public void realizarCompra(){
        GerenciadorDePedidos ger_ped = this.fazerPedido();
        Pedido pedido = ger_ped.getPedido();    

        Pagamento pagamento = new Pagamento();
        Pedido pedido_pago = pagamento.processarPagamento(pedido);
        
        boolean verificacao = pedido_pago.verificacaoCompra();
        if(verificacao){
            historicoCompras.add(pedido);
            carrinho.limparCarrinho();
            for(Vendedor vendedor: ger_ped.getLista_vendedores()){
                vendedor.realizaVenda(pedido_pago);
            }
        }
        else{
            System.out.println("Compra não realizada.");  
            pedido.setStatus(StatusPedido.CANCELADO);          
        } 
    }

    public void adicionarListaDeDesejos(Produto produto){
        this.listaDesejos.add(produto);
    }

    public void mostrarHistorico(){
        System.out.println("=-=-=-=-=-=-=-=-=-=- Lista de Pedidos =-=-=-=-=--=-==-=-=-");
        for (Pedido pedido: this.getHistoricoCompras()){
            pedido.mostrarPedido();
            System.out.println("\n");
        }
    }

    public void adicionarAoHistorico(Pedido pedido){
        this.historicoCompras.add(pedido);
    }
    public boolean temHistorico(){
        return !historicoCompras.isEmpty();
    }

    public void AdicionarDesejoAoCarrinho(Produto produto){
        Produto produto_buscado = this.getCarrinhoDeCompras().encontraProdutoNoCarrinho(produto.getId_produto());
        if(produto.equals(produto_buscado)){
            System.out.println("O produto já está no carrinho.");
        }
        else{
            this.carrinho.adicionarAoCarrinho(produto_buscado);
            System.out.println("Produto adicionado ao carrinho de compras com sucesso.");
        }
    }
    
}
