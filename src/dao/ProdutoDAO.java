package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;
import model.Vendedor;

public class ProdutoDAO {
    private Connection conection;

    public ProdutoDAO(Connection conection){
        this.conection = conection;
    }

    public void insertProduto(Produto produto) throws SQLException{
        String sql = "insert into produtos(nome,descricao,preco,categoria,cnpj,quantidade) values(?,?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, produto.getNome());
        statement.setString(2, produto.getDescricao());
        statement.setDouble(3, produto.getPreco());
        statement.setString(4, produto.getCategoria());
        statement.setLong(5, produto.getVendedor().getCnpj());
        statement.setLong(6, produto.getVendedor().getGer_est().getEstoque().get(produto));
        statement.execute();

        produto.setId_produto(idTabelaProduto(produto));
    }

    public ArrayList<Produto> buscaNomeProduto(String nome) throws SQLException{
        String sql = "select * from produtos where nome = ?;";

        PreparedStatement statement = conection.prepareStatement(sql);

        statement.setString(1,nome);

        return pesquisaProduto(statement);
    }

    public ArrayList<Produto> buscaCategoriaProduto(String categoria) throws SQLException{
        String sql = "select * from produtos where categoria = ?;";

        PreparedStatement statement = conection.prepareStatement(sql);

        statement.setString(1,categoria);

        return pesquisaProduto(statement);
    }

    public int idTabelaProduto(Produto produto) throws SQLException{
        String sql = "select * from produtos where nome = ? and cnpj = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);

        statement.setString(1, produto.getNome());
        statement.setLong(2, produto.getVendedor().getCnpj());

        ArrayList<Produto> foo = pesquisaProduto(statement);
        
        return foo.get(0).getId_produto();
    }

    private ArrayList<Produto> pesquisaProduto(PreparedStatement statement) throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        statement.execute();
        ResultSet results = statement.getResultSet();

        while (results.next()) {
            int id = results.getInt("id");
            String nome = results.getString("nome");
            String descricao = results.getString("descricao");
            String categoria = results.getString("categoria");
            int cnpj = results.getInt("cnpj");
            Double preco = results.getDouble("preco");

            Connection conec = new Conexao().getConnection();
            
            VendedorDAO auxDao = new VendedorDAO(conec);
            
            Vendedor vendedor = auxDao.selectPorCNPJ(cnpj);
            

            Produto produtoDoBancoDB = new Produto(nome,descricao,preco,categoria,vendedor);
            produtoDoBancoDB.setId_produto(id);
            produtos.add(produtoDoBancoDB);
        }

        return produtos;
    }

    public void removeQuanProduto(Produto produto, int quantidade_comprada) throws SQLException{
        String sql = "update produtos set id = ? and cnpj = ? where quantidade = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, produto.getId_produto());
        statement.setLong(2, produto.getVendedor().getCnpj());

        int quantidade_atual = produto.getVendedor().getGer_est().getEstoque().get(produto);

        statement.setInt(3, quantidade_atual - quantidade_comprada);
        statement.execute();
    }

    public void addQuanProduto(Produto produto, int quantidade_comprada) throws SQLException{
        String sql = "update produtos set id = ? and cnpj = ? where quantidade = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, produto.getId_produto());
        statement.setLong(2, produto.getVendedor().getCnpj());

        int quantidade_atual = produto.getVendedor().getGer_est().getEstoque().get(produto);

        statement.setInt(3, quantidade_atual + quantidade_comprada);
        statement.execute();
    }

}
