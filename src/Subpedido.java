import java.util.ArrayList;

public class Subpedido extends Pedido { 
    private Vendedor vendedor;
    ArrayList<Produto> lista_produtos = new ArrayList<>();

    public Subpedido(Cliente cliente, Vendedor vendedor) {
        super(cliente);
        this.vendedor = vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Produto> getLista_produtos() {
        return lista_produtos;
    }

    public void setLista_produtos(ArrayList<Produto> lista_produtos) {
        this.lista_produtos = lista_produtos;
    }

    public Subpedido criaSubpedido(ArrayList<Produto>lista, Vendedor vendedor){
        for(Produto produto:lista){
            if(produto.getVendedor() == vendedor){
                this.lista_produtos.add(produto);
            }
        }
        return this;
    }


    
}
