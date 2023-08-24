import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeEstoque {
    private Map<Produto, Integer> estoque = new HashMap<>();

    public void adicionarProdutoNoEstoque(Produto produto, int quantidade){
        estoque.put(produto, quantidade);
    }

    public int verificaDisponibilidade(Produto produto, int quantidade){
        int quantidade_em_estoque = estoque.get(produto);
        int validacao = 0;
        if(quantidade > 0){
            if(quantidade < quantidade_em_estoque){
                validacao = 1;
            }
            else{
                System.out.println("Produto indisponível em estoque.");
            }
        }
        return validacao;
    }
    public void atualizaEstoque(Pedido pedido){
        Map<Produto, Integer> lista_produtos = pedido.getCarrinho().getLista_produtos();
        for(Map.Entry<Produto, Integer> par: lista_produtos.entrySet()){
            int quantidade_comprada = par.getValue();
            Produto produto = par.getKey();
            int quantidade_estoque = estoque.get(produto);
            estoque.put(produto, quantidade_estoque-quantidade_comprada);
        }
    }
}
