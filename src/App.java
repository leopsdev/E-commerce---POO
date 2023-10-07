import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {

        Vendedor vend1 = new Vendedor("nome8","nome.com","nome@gmail.com","405542",405502,"nome, um nome pra ti");

        Produto prod1 = vend1.cadastrarProduto();

        //Cliente cliente1 = new Cliente("teste14","rua teste, avenida teste","teste@gmail.com","123teste",40028922);

        Connection conexao = new Conexao().getConnection();
        //ClienteDAO clienteDAO = new ClienteDAO(conexao);
        VendedorDAO vendedorDAO = new VendedorDAO(conexao);
        vendedorDAO.insertVendedor(vend1);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        produtoDAO.insertProduto(prod1);

        // ClienteDAO.insertCliente(cliente1);
        
        
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
