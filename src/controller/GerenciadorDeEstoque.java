package controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.Conexao;
import dao.ProdutoDAO;
import model.Pedido;
import model.Produto;

public class GerenciadorDeEstoque {
    private HashMap<Produto, Integer> estoque = new HashMap<>();
    
    public GerenciadorDeEstoque(HashMap<Produto, Integer> estoque){
        this.estoque = estoque;
    }
    public HashMap<Produto, Integer> getEstoque(){
        return this.estoque;
    }
    public void setEstoque(HashMap<Produto, Integer> estoque){
        this.estoque = estoque;
    }
    public void adicionarProdutoNoEstoque(Produto produto, int quantidade){
        estoque.put(produto, quantidade);
    }
    public boolean verificaDisponibilidade(Produto produto, int quantidade){
        int quantidade_em_estoque = this.estoque.get(produto);
        boolean validacao = false;
        if(quantidade > 0){
            if(quantidade < quantidade_em_estoque){
                validacao = true;
            }
            else{
                System.out.println("Produto indisponível em estoque.");
            }
        }
        return validacao;
    }
    public boolean verificaDisponibilidade2(Produto produto, int quantidade) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        int quantidade_em_estoque = produtoDAO.quantEmEstoque(produto.getId_produto());
        boolean validacao = false;
        if(quantidade > 0){
            if(quantidade < quantidade_em_estoque){
                validacao = true;
            }
            else{
                System.out.println("Produto indisponível em estoque.");
            }
        }
        return validacao;
    }
    public void atualizaEstoque(Pedido pedido){
        Map<Produto, Integer> lista_produtos = pedido.getCliente().getCarrinhoDeCompras().getLista_produtos();
        for(Map.Entry<Produto, Integer> par: lista_produtos.entrySet()){
            int quantidade_comprada = par.getValue();
            Produto produto = par.getKey();
            int quantidade_estoque = estoque.get(produto);
            estoque.put(produto, quantidade_estoque-quantidade_comprada);
        }
    }
}
