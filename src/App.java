import java.sql.Connection;

import dao.Conexao;

public class App {
    public static void main(String[] args) throws Exception {
        
        Vendedor vend1 = new Vendedor("nome","nome.com","nome@gmail.com","4002",4002,"nome, um nome pra ti");

        Produto prod1 = vend1.cadastrarProduto();

        Cliente cliente1 = new Cliente("teste1","rua teste, avenida teste","teste@gmail.com","123teste",40028922);

        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        usuarioDao.insertCliente(cliente1);
        

        cliente1.adicionarListaDeDesejos(prod1);

        boolean verificacao = cliente1.getCarrinhoDeCompras().adicionarAoCarrinho(prod1);
        if(verificacao){
            cliente1.realizarCompra();
        }
        
        conexao.close();
    }
}
