import java.util.ArrayList;
import java.util.HashSet;

public class GerenciadorDePedidos {
    private Pedido pedido;
    ArrayList<Produto> lista_produtos = new ArrayList<>();
    HashSet<Vendedor> lista_vendedores = new HashSet<>();

    public GerenciadorDePedidos(Pedido pedido){
        this.pedido = pedido;
        this.lista_produtos = pedido.getCliente().getCarrinhoDeCompras().getProdutos();
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public ArrayList<Produto> getLista_produtos() {
        return lista_produtos;
    }
    public void setLista_produtos(ArrayList<Produto> lista_produtos) {
        this.lista_produtos = lista_produtos;
    }
    public HashSet<Vendedor> getLista_vendedores() {
        return lista_vendedores;
    }
    public void setLista_vendedores(HashSet<Vendedor> lista_vendedores) {
        this.lista_vendedores = lista_vendedores;
    }
    
    public void organizaPedidos(){
        for(Produto produto:lista_produtos){
            lista_vendedores.add(produto.getVendedor());
        }
        for(Vendedor vendedor:lista_vendedores){
            Subpedido novo_subpedido = new Subpedido(pedido.getCliente(), vendedor);
            novo_subpedido.criaSubpedido(lista_produtos, vendedor);
            vendedor.adicionarListaDePedido(novo_subpedido);
        }
    }

}
