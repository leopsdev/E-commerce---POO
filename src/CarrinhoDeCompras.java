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

    public void modificarCarrinho(Pedido pedido, Vendedor vendedor){
        pedido.mostrarPedido();
        int escolha1 = 0;
        int escolha2 = 0;
        int id_removido;
        int id_alterado;
        int quant_alterada;

        while(escolha1 != 2){
            System.out.println("[1] - Adicionar produto\n[2] - Remover produto\n[3] - Alterar quantidade de um produto\n\n");
            System.out.println("Digite uma das opções acima: ");
            escolha2 = scan.nextInt();
            while(escolha2 != 1 & escolha2 != 2){
                System.out.println("Opção inválida. Digite uma das opções fornecidas: ");
                escolha2 = scan.nextInt();
            }
            switch(escolha2){
                case 1:
                    System.out.println("Função em desenvolvimento."); // O vendedor em questão deve mostrar quais os produtos que ele tem.
                    break;
                case 2:
                    System.out.println("Digite o id do produto que deseja remover: ");
                    id_removido = scan.nextInt();
                    Produto produto_removido = encontraProdutoNoCarrinho(id_removido);
                    this.removerProduto(produto_removido);
                    break;
                case 3:
                    System.out.println("Digite o id do produto cuja quantidade em seu carrinho deseja alterar: ");
                    id_alterado = scan.nextInt();
                    System.out.println("Digite a nova quantidade deste produto que deseja em seu carrinho: ");
                    quant_alterada = scan.nextInt();

                    Produto produto_quant_alterada = encontraProdutoNoCarrinho(id_alterado);
                    boolean verificaDisponibilidade = vendedor.getEstoque().verificaDisponibilidade(produto_quant_alterada, quant_alterada);
                    while(verificaDisponibilidade == false){
                        System.out.println("Não temos essa quantidade em estoque. Digite outra quantidade: ");
                        quant_alterada = scan.nextInt();
                        verificaDisponibilidade = vendedor.getEstoque().verificaDisponibilidade(produto_quant_alterada, quant_alterada);
                    }
                    this.lista_produtos.put(produto_quant_alterada, quant_alterada);
                    break;
            }
            System.out.println("Deseja continuar modificando o carrinho? [1] - Sim; [2] - Não");
            escolha1 = scan.nextInt();
        }
        pedido.setCarrinho(this);
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
        return total;
    }
    public void limparCarrinho(){
        this.lista_produtos.clear();
    }
}