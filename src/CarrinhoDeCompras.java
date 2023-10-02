import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CarrinhoDeCompras{

    private Map<Produto, Integer> lista_produtos = new HashMap<>();
    private Scanner scan = new Scanner(System.in);
    public Map<Produto, Integer> getLista_produtos() {
        return lista_produtos;
    }
    public void setLista_produtos(Map<Produto, Integer> lista_produtos) {
        this.lista_produtos = lista_produtos;
    }

    public void adicionarAoCarrinho(Produto produto){
        System.out.println("Digite quantas unidades deseja comprar: ");
        int quantidade = scan.nextInt();
        boolean verificaDisponibilidade = produto.getVendedor().getEstoque().verificaDisponibilidade(produto, quantidade);
        int escolha1 = 0;
        while(verificaDisponibilidade == false){
            System.out.println("Você selecionou uma quantidade que não possuímos em estoque. Selecione uma nova quantidade ou cancele a adição.");
            System.out.println("[1] - Para modificar a quantidade adicionada\n[2] - Para cancelar a adição");
            System.out.println("Selecione uma das opções acima: ");
            escolha1 = scan.nextInt();
            while(escolha1 != 1 & escolha1 != 2){
                System.out.println("Opção inválida. Selecione uma das opções fornecidas: ");
                escolha1 = scan.nextInt();             
            }
            if(escolha1 == 1){
                System.out.println("Digite quantas unidades deste produto deseja comprar: ");
                quantidade = scan.nextInt();
                verificaDisponibilidade = produto.getVendedor().getEstoque().verificaDisponibilidade(produto, quantidade);
            }
            else{
                break;
            }
        }
        if(verificaDisponibilidade){
            this.lista_produtos.put(produto, quantidade);
        }
        else{
            System.out.println("Adição cancelada.");
        }
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
    
    public void limparCarrinho(){
        this.lista_produtos.clear();
    }
}
