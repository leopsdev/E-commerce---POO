import java.sql.Connection;

import dao.Conexao;

public class App {
    public static void main(String[] args) throws Exception {
        
        Vendedor vend1 = new Vendedor("Caixa.com","caixas.com","caixacom@gmail.com",01,"Caixas.com","Loja de caixas");

        Produto prod1 = vend1.cadastrarProduto();

        Cliente cliente1 = new Cliente("teste1","Triangulo das Bermudas","leopereiraesilca@gmail.com",40020322,"null");

        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        usuarioDao.insertCliente(cliente1);
        

        cliente1.adicionarListaDeDesejos(prod1);

        cliente1.adicionarAoCarrinho(prod1, vend1);
        
        conexao.close();
    }
}
