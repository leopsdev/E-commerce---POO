package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Produto;
import model.Vendedor;

public class ProdutoDAO {
    private Connection conection;

    public ProdutoDAO(Connection conection){
        this.conection = conection;
    }

    public ArrayList<Produto> selectAllProdutos() throws SQLException{
        String sql = "select * from produtos;";
        PreparedStatement statement = conection.prepareStatement(sql);

        return pesquisaProduto(statement);
    }

    public ArrayList<Produto> selectProdutoVendedor(Long cnpj) throws SQLException{
        String sql = "select * from produtos where cnpj = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, cnpj);

        return pesquisaProduto(statement);
    }

    public ArrayList<Produto> selectProdutoId(int id) throws SQLException{
        String sql = "select * from produtos where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, id);

        return pesquisaProduto(statement);
    }

    public int quantEmEstoque(int id) throws SQLException{
        String sql = "select * from produtos where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        ResultSet results = statement.getResultSet();
        
        if (results.next()== true) {
            JOptionPane.showMessageDialog(null, "Atualize a tabela de produtos.");
            return results.getInt("quantidade");
        } else {
            return results.getInt("quantidade");
        }
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
            int quantidade = results.getInt("quantidade");

            Connection conec = new Conexao().getConnection();
            
            VendedorDAO auxDao = new VendedorDAO(conec);
            
            Vendedor vendedor = auxDao.selectPorCNPJ(cnpj);
            

            Produto produtoDoBancoDB = new Produto(nome,descricao,preco,categoria,vendedor);
            produtoDoBancoDB.setId_produto(id);
            produtoDoBancoDB.setQuant(quantidade);
            produtos.add(produtoDoBancoDB);
        }

        return produtos;
    }

    public void removeQuanProduto(Produto produto, int quantidade_comprada) throws SQLException{
        String sql = "update produtos set quantidade = ? where id = ? and cnpj = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(2, produto.getId_produto());
        statement.setLong(3, produto.getVendedor().getCnpj());

        int quantidade_atual = quantEmEstoque(produto.getId_produto());
        int nova_quant = quantidade_atual - quantidade_comprada;

        statement.setInt(1, nova_quant);
        statement.execute();
    }

    public void addQuanProduto(Produto produto, int quantidade_adicionada) throws SQLException{
        String sql = "update produtos set quantidade = ? where id = ? and cnpj = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(2, produto.getId_produto());
        statement.setLong(3, produto.getVendedor().getCnpj());

        int quantidade_atual = quantEmEstoque(produto.getId_produto());
        int nova_quant = quantidade_atual + quantidade_adicionada;

        statement.setLong(1, nova_quant);
        statement.execute();
    }

}
