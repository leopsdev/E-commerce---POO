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
        String sql = "insert into usuario(email,senha,nome,cpf,endereco,info_pagamento) values(?,?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, cliente.getEmail());
        statement.setString(2, cliente.getSenha());
        statement.setString(3, cliente.getNome());
        statement.setLong(4, cliente.getCpf());
        statement.setString(5, cliente.getEndereco());
        statement.setString(6, cliente.getInfo_pagamento());
        statement.execute();
        
    }

    /* preciso reformular */
    public void insertVendedor(Vendedor vendedor) throws SQLException{
        String sql = "insert into usuario(email,senha,nome,cpf) values(?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, vendedor.getEmail());
        statement.setString(2, vendedor.getSenha());
        statement.setString(3, vendedor.getNome());
        statement.setLong(4, vendedor.getCnpj());
        statement.execute();
        
    }

    public boolean existeUsuario(Usuario usuario) throws SQLException{
        String sql = "select * from usuario where email = ? and senha = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        
        ResultSet result = statement.getResultSet();

        return result.next();
    }

    /* metodo não finalizado ainda */
    public void updateUsuario(Usuario usuario) throws SQLException{
        String sql = "update usuario set email = ? and senha = ? where id = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        //statement.setLong(3, usuario.getProximoID); -> não tem getter para pegar o id do usuario, 
        //tbm não há garantia que esses ids(do codigo e da tabela) sejam iguais. observar isso tbm;
        statement.execute();
    }

    public void deliteUsuario(Usuario usuario) throws SQLException{
        String sql = "delete from usuario where id = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        //statement.setInt(1, usuario.getProximoID());
        statement.execute();
    }

    public ArrayList<Cliente> selectAllCliente() throws SQLException{
        String sql = "select * from usuario;";
        
        PreparedStatement statement = conection.prepareStatement(sql);

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        statement.execute();
        ResultSet results = statement.getResultSet();

        while (results.next()) {
            //int id = results.getInt("id");
            String nome = results.getString("nome");
            String email = results.getString("email");
            //String senha = results.getString("senha"); Há necessidade de mostrar a senha?
            int cpf = results.getInt("cpf");
            String endereco = results.getString("endereco");
            String info_pagamento = results.getString("info_pagamento");

            Cliente clienteDoBancoDB = new Cliente(nome,endereco,email,cpf,info_pagamento);
            clientes.add(clienteDoBancoDB);
        }

        return clientes;
    }


}
