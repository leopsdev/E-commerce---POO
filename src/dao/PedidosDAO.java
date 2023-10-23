package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Produto;

public class PedidosDAO {
    private Connection conection;

    public PedidosDAO(Connection conection){
        this.conection = conection;
    }

    public void insertPedido(Cliente cliente, Produto produto, int quant_selecionada, String status) throws SQLException{
        String sql = "insert into pagamentos(id_produto,cnpj_vendedor,quant_selecionada,preco_produto,id_cliente,email_cliente,status) values(?,?,?,?,?,?,?);";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, produto.getId_produto());
        statement.setLong(2, produto.getVendedor().getCnpj());
        statement.setInt(3, quant_selecionada);
        statement.setDouble(4, produto.getPreco());
        statement.setInt(5, cliente.getId());
        statement.setString(6, cliente.getEmail());
        statement.setString(7, status);

        statement.execute();
    }

    public void deletePedido(Cliente cliente, Produto produto) throws SQLException{ //lembrar que essa função vai ser usada varias vezes pois os pedidos no carrinho estão em uma array
        String sql = "delete from pagamentos where id_produto = ? and id_cliente = ?;";
        
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setInt(1, produto.getId_produto());
        statement.setInt(2, cliente.getId());
        statement.execute();
    }

    public void updateStatus(int id_cliente, int id_produto, String status) throws SQLException{ //lembrar do lembrete acima pra essa também
        String sql = "update pagamentos set status = ? where id_produto = ? and id_cliente = ?;";

        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setString(1, status);
        statement.setInt(2, id_produto);
        statement.setInt(3, id_cliente);
        statement.execute();
        
    }
    public ArrayList<PedidoAux> selectProdutoVendedor(Long cnpj) throws SQLException{
        String sql = "select * from pagamentos where cnpj_vendedor = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, cnpj);
        statement.execute();
        ResultSet results = statement.getResultSet();

        ArrayList<PedidoAux> pedidoAuxs = new ArrayList<>();
        

        while (results.next()) {
            int id_produto = results.getInt("id_produto");
            long cnpj_vendedor = results.getInt("cnpj_vendedor");
            int quant_selecionada = results.getInt("quant_selecionada");
            double preco_produto = results.getDouble("preco_produto");
            int id_cliente = results.getInt("id_cliente");
            String email_cliente = results.getString("email_cliente");
            String status = results.getString("status");

            PedidoAux pedidoAux = new PedidoAux(id_produto,cnpj_vendedor,quant_selecionada,preco_produto,id_cliente,email_cliente,status);
            pedidoAuxs.add(pedidoAux);
        }

        return pedidoAuxs;
    }
    public ArrayList<PedidoAux> selectProdutoCliente(int id) throws SQLException{
        String sql = "select * from pagamentos where id_cliente = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        ResultSet results = statement.getResultSet();

        ArrayList<PedidoAux> pedidoAuxs = new ArrayList<>();
        

        while (results.next()) {
            int id_produto = results.getInt("id_produto");
            long cnpj_vendedor = results.getInt("cnpj_vendedor");
            int quant_selecionada = results.getInt("quant_selecionada");
            double preco_produto = results.getDouble("preco_produto");
            int id_cliente = results.getInt("id_cliente");
            String email_cliente = results.getString("email_cliente");
            String status = results.getString("status");

            PedidoAux pedidoAux = new PedidoAux(id_produto,cnpj_vendedor,quant_selecionada,preco_produto,id_cliente,email_cliente,status);
            pedidoAuxs.add(pedidoAux);
        }

        return pedidoAuxs;
    }

    public ArrayList<PedidoAux> selectProdutoParaHistorico(int id) throws SQLException{
        String sql = "select * from pagamentos where id_cliente = ? and status = ?;";
        PreparedStatement statement = conection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.setString(2, "RECEBIDO");
        statement.execute();
        ResultSet results = statement.getResultSet();

        ArrayList<PedidoAux> pedidoAuxs = new ArrayList<>();
        

        while (results.next()) {
            int id_produto = results.getInt("id_produto");
            long cnpj_vendedor = results.getInt("cnpj_vendedor");
            int quant_selecionada = results.getInt("quant_selecionada");
            double preco_produto = results.getDouble("preco_produto");
            int id_cliente = results.getInt("id_cliente");
            String email_cliente = results.getString("email_cliente");
            String status = results.getString("status");

            PedidoAux pedidoAux = new PedidoAux(id_produto,cnpj_vendedor,quant_selecionada,preco_produto,id_cliente,email_cliente,status);
            pedidoAuxs.add(pedidoAux);
        }

        return pedidoAuxs;
    }

}
