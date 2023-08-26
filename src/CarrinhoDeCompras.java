import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras{
    private Map<Produto, Integer> lista_produtos = new HashMap<>();
    public Map<Produto, Integer> getLista_produtos() {
        return lista_produtos;
    }
    public void setLista_produtos(Map<Produto, Integer> lista_produtos) {
        this.lista_produtos = lista_produtos;
    }
    public void adicionarProduto(Produto produto, int quantidade) {
        this.lista_produtos.put(produto, quantidade);
    }
    public void removerProduto(Produto produto){
        this.lista_produtos.remove(produto);
    }
    public ArrayList<Produto> getProdutos(){
        ArrayList<Produto> produtos = new ArrayList<>();
        for(Map.Entry<Produto, Integer>par: lista_produtos.entrySet()){
            produtos.add(par.getKey());
        }
        return produtos;
    }
    public ArrayList<Integer> getQuantidadeProdutoCompra(){
        ArrayList<Integer> quantidades = new ArrayList<>();
        for(Map.Entry<Produto, Integer>par: lista_produtos.entrySet()){
            quantidades.add(par.getValue());
        }
        return quantidades;
    }
    public double calculaTotalCompra(){
        ArrayList<Produto> produtos = getProdutos();
        double total = 0;
        for(Produto produto: produtos){
            total += produto.getPreco();
        }
        return total;
    }
    public void limparCarrinho(){
        this.lista_produtos.clear();
    }
}