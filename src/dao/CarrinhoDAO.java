package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Produto;
import model.Vendedor;

public class CarrinhoDAO {
    private Connection conection;

    public CarrinhoDAO(Connection conection){
        this.conection = conection;
    }

    public void insertCarrinhe(Cliente cliente, Produto produto, int quant_selecionada) throws SQLException{
        String sql = "insert into carrinhos(id_produto,cnpj_vendedor,quant_selecionada,preco_produto,id_cliente,email_cliente) values(?,?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, produto.getId_produto());
        statement.setLong(2, produto.getVendedor().getCnpj());
        statement.setInt(3, quant_selecionada);
        statement.setDouble(4, produto.getPreco());
        statement.setInt(5, cliente.getId());
        statement.setString(6, cliente.getEmail());

        statement.execute();
    }

    public void deleteProdutoCarrinhe(Cliente cliente, Produto produto) throws SQLException{
        String sql = "delete from carrinhos where id_produto = ? and id_cliente = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, produto.getId_produto());
        statement.setInt(2, cliente.getId());
        statement.execute();
    }

    public void updateQuantidade(Cliente cliente, Produto produto, int quant_selecionada) throws SQLException{
        String sql = "update carrinhos set quant_selecionada = ? where id_produto = ? and id_cliente = ?;";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, quant_selecionada);
        statement.setInt(2, produto.getId_produto());
        statement.setInt(3, cliente.getId());
        statement.execute();
        
    }
    public ArrayList<Integer> selectQuantProd(Cliente cliente) throws SQLException{
        ArrayList<Integer> quantidades = new ArrayList<>();
        String sql = "select * from carrinhos where id_cliente = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, cliente.getId());
        statement.execute();

        ResultSet result = statement.getResultSet();

        while (result.next()) {
            quantidades.add(result.getInt("quant_selecionada"));
        }

        return quantidades;
    }

    public ArrayList<Produto> selectProdutoIds(Cliente cliente) throws SQLException{
        ArrayList<Integer> ids_produtos = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "select * from carrinhos where id_cliente = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, cliente.getId());

        statement.execute();
        
        ResultSet result = statement.getResultSet();

        while (result.next()) {
            ids_produtos.add(result.getInt("id_produto"));
        }

        ProdutoDAO produtoDAO = new ProdutoDAO(conection);
        for (Integer ids : ids_produtos) {
            ArrayList<Produto> aux = produtoDAO.selectProdutoId(ids);
            Produto auxProd = aux.get(0);
            produtos.add(auxProd);
        }
        
        return produtos;
    }

}
