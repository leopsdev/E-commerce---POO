import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 

public class UsuarioDAO {
    private final Connection conection;

    public UsuarioDAO(Connection conection){
        this.conection = conection;
    }

    public void insertCliente(Cliente cliente) throws SQLException{
        String sql = "insert into usuario(email,senha,nome,cpf,endereco) values(?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cliente.getEmail());
        statement.setString(2, cliente.getSenha());
        statement.setString(3, cliente.getNome());
        statement.setLong(4, cliente.getCpf());
        statement.setString(5, cliente.getEndereco());
        statement.execute();

        cliente.setId(idTabelaCliente(cliente));
    }

    /* preciso reformular */
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

    public boolean existeCliente(Cliente cliente) throws SQLException{
        String sql = "select * from usuario where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cliente.getEmail());
        statement.setString(2, cliente.getSenha());
        statement.execute();
        
        ResultSet result = statement.getResultSet();

        return result.next();
    }

    public int idTabelaCliente(Cliente cliente) throws SQLException{
        String sql = "select * from usuario where email = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cliente.getEmail());
        ArrayList<Cliente> foo = pesquisaCliente(statement);
        
        return foo.get(0).getId();
    }

    public int idTabelaVendedor(Vendedor vendedor) throws SQLException{
        String sql = "select * from vendedor where email = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        ArrayList<Vendedor> foo = pesquisaVendedor(statement);
        
        return foo.get(0).getId();
    }

    public boolean existeVendedor(Vendedor vendedor) throws SQLException{
        String sql = "select * from vendedor where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        statement.setString(2, vendedor.getSenha());
        statement.execute();
        
        ResultSet result = statement.getResultSet();

        return result.next();
    }

    public void updateCliente(Cliente cliente) throws SQLException{
        String sql = "update usuario set email = ? and senha = ? where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cliente.getEmail());
        statement.setString(2, cliente.getSenha());
        statement.setLong(3, cliente.getId());
        statement.execute();
    }

    public void updateVendedor(Vendedor vendedor) throws SQLException{
        String sql = "update usuario set email = ? and senha = ? where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        statement.setString(2, vendedor.getSenha());
        statement.setLong(3, vendedor.getId());
        statement.execute();
    }

    public void deleteCliente(Cliente cliente) throws SQLException{
        String sql = "delete from usuario where id = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, cliente.getId());
        statement.execute();
    }

    public void deleteVendedor(Vendedor vendedor) throws SQLException{
        String sql = "delete from usuario where id = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, vendedor.getId());
        statement.execute();
    }

    public ArrayList<Cliente> selectAllCliente() throws SQLException{
        String sql = "select * from usuario;";
        
        PreparedStatement statement = conection.prepareStatement(sql);

        return pesquisaCliente(statement);
    }

    private ArrayList<Cliente> pesquisaCliente(PreparedStatement statement) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        statement.execute();
        ResultSet results = statement.getResultSet();

        while (results.next()) {
            int id = results.getInt("id");
            String nome = results.getString("nome");
            String email = results.getString("email");
            String senha = results.getString("senha");
            int cpf = results.getInt("cpf");
            String endereco = results.getString("endereco");

            Cliente clienteDoBancoDB = new Cliente(nome,endereco,email,senha,cpf);
            clienteDoBancoDB.setId(id);
            clientes.add(clienteDoBancoDB);
        }

        return clientes;
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
