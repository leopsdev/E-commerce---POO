import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.Math;

public class CarrinhoDeCompras{
    private Cliente cliente;

    private Map<Produto, Integer> lista_produtos = new HashMap<>();
    private Scanner scan = new Scanner(System.in);
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

    public Produto encontraProdutoNoCarrinho(int id){
        Produto produto_removido = null;
        produto_removido = this.buscaNoCarrinho(id);
        if(produto_removido == null){
            System.out.println("Produto não encontrado.");    // Fazer uma validação pra o caso de digitarem um id que não existe no carrinho.
            int id_removido = 0;
            while(produto_removido == null){        // Fazer isso recursivamente.
                System.out.println("Digite o id do produto que deseja remover do carrinho: ");
                id_removido = scan.nextInt();
                produto_removido = buscaNoCarrinho(id_removido);
            }
        }
        return produto_removido;
    }
    public Produto buscaNoCarrinho(int id){
        Produto produto_removido = null; // Implementar uma busca binária, posteriormente (complexidade: O(logn))
        for(Map.Entry<Produto, Integer> par: this.lista_produtos.entrySet()){
            if(par.getKey().getId_produto() == id){
                produto_removido = par.getKey();
            }
        }
        return produto_removido;
    }
    public double calculaTotalCompra(){
        ArrayList<Produto> produtos = getProdutos();
        double total = 0;
        for(Produto produto: produtos){
            total += produto.getPreco();
        }
        boolean temHistorico = cliente.temHistorico();
        total = aplicarDesconto(total,temHistorico);
        total = aplicarFrete(total, temHistorico);
        return total;
    }
    public void limparCarrinho(){
        this.lista_produtos.clear();
    }
    public double aplicarDesconto(double total, Boolean temHistorico){
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
    public double aplicarFrete(double total, Boolean temHistorico){
        if (temHistorico != true) {
            total += (Math.random()*100);
        }
        return total;
    }
}
