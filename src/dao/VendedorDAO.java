package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Vendedor;

public class VendedorDAO{
    private Connection conection;

    public VendedorDAO(Connection conection){
        this.conection = conection;
    }

    public void insertVendedor(Vendedor vendedor) throws SQLException{
        String sql = "insert into vendedor(email,senha,nome_loja,cnpj,descricao_empresa,endereco) values(?,?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        statement.setString(2, vendedor.getSenha());
        statement.setString(3, vendedor.getNome());
        statement.setLong(4, vendedor.getCnpj());
        statement.setString(5, vendedor.getDescricao_empresa());
        statement.setString(6, vendedor.getEndereco());
        statement.execute();

        vendedor.setId(idTabelaVendedor(vendedor));
        
    }

    public int idTabelaVendedor(Vendedor vendedor) throws SQLException{
        String sql = "select * from vendedor where email = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        ArrayList<Vendedor> foo = pesquisaVendedor(statement);
        
        return foo.get(0).getId();
    }

    public Vendedor selectPorCNPJ(int cnpj) throws SQLException{
        String cnpjString = String.valueOf(cnpj);
        String sql = "select * from vendedor where cnpj = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cnpjString);
        ArrayList<Vendedor> foo = pesquisaVendedor(statement);

        return foo.get(0);
    }
    public Vendedor selectPorEmailSenha(String email, String senha) throws SQLException{
        String sql = "select * from vendedor where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, senha);
        ArrayList<Vendedor> foo = pesquisaVendedor(statement);

        return foo.get(0);
    }

    public boolean existeVendedor(String email, String senha) throws SQLException{
        String sql = "select * from vendedor where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, senha);
        statement.execute();
        
        ResultSet result = statement.getResultSet();

        return result.next();
    }

    public void updateVendedor(Vendedor vendedor) throws SQLException{
        String sql = "update usuario set email = ? and senha = ? where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        statement.setString(2, vendedor.getSenha());
        statement.setLong(3, vendedor.getId());
        statement.execute();
    }

    public void updateVendedorNome(Vendedor vendedor, String nome) throws SQLException{
        String sql = "update vendedor set nome_loja = ? where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, vendedor.getEmail());
        statement.setString(3, vendedor.getSenha());
        statement.execute();
    }
    public void updateVendedorEmail(Vendedor vendedor, String email) throws SQLException{
        String sql = "update vendedor set email = ? where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, vendedor.getEmail());
        statement.setString(3, vendedor.getSenha());
        statement.execute();
    }
    public void updateVendedorSenha(Vendedor vendedor, String senha) throws SQLException{
        String sql = "update vendedor set senha = ? where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, senha);
        statement.setString(2, vendedor.getEmail());
        statement.setString(3, vendedor.getSenha());
        statement.execute();
    }

    public void deleteVendedor(Vendedor vendedor) throws SQLException{
        String sql = "delete from usuario where id = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, vendedor.getId());
        statement.execute();
    }

    public ArrayList<Vendedor> selectAllVendedor() throws SQLException{
        String sql = "select * from vendedor;";
        
        PreparedStatement statement = conection.prepareStatement(sql);

        return pesquisaVendedor(statement);
    }

    private ArrayList<Vendedor> pesquisaVendedor(PreparedStatement statement) throws SQLException {
        ArrayList<Vendedor> vendedors = new ArrayList<Vendedor>();

        statement.execute();
        ResultSet results = statement.getResultSet();

        while (results.next()) {
            int id = results.getInt("id");
            String nome = results.getString("nome_loja");
            String email = results.getString("email");
            String senha = results.getString("senha");
            int cnpj = results.getInt("cnpj");
            String endereco = results.getString("endereco");
            String descricao_empresa = results.getString("descricao_empresa");

            Vendedor vendedorDoBancoDB = new Vendedor(nome,endereco,email,senha,cnpj,descricao_empresa);
            vendedorDoBancoDB.setId(id);
            vendedors.add(vendedorDoBancoDB);
        }

        return vendedors;
    }
    
}
