import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {

        Vendedor vend1 = new Vendedor("nome5","nome.com","nome@gmail.com","4002",4002,"nome, um nome pra ti");

        //Produto prod1 = vend1.cadastrarProduto();

        Cliente cliente1 = new Cliente("teste13","rua teste, avenida teste","teste@gmail.com","123teste",40028922);

        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(conexao);
        VendedorDAO vendedorDAO = new VendedorDAO(conexao);

        // ClienteDAO.insertCliente(cliente1);
        // vendedorDAO.insertVendedor(vend1);
        
        // System.out.println("Contas Clientes:");
        // for (Cliente atual : usuarioDao.selectAllCliente()) {
        //     System.out.println("Email:"+atual.getEmail()+" Nome:"+atual.getNome()+ " id:" + atual.getId());
        // }

        // System.out.println("Contas Vende:");
        // for (Vendedor atual : usuarioDao.selectAllVendedor()) {
        //     System.out.println("Email:"+atual.getEmail()+" Nome:"+atual.getNome()+ " id:" + atual.getId());
        // }


        //cliente1.adicionarListaDeDesejos(prod1);

        //boolean verificacao = cliente1.getCarrinhoDeCompras().adicionarAoCarrinho(prod1);
        // if(verificacao){
        //     cliente1.realizarCompra();
        // }
        
        conexao.close();
    }
}
