import java.sql.Connection;
import java.util.ArrayList;

import Interface.TelaEntrada;
import dao.Conexao;
import dao.ProdutoDAO;
import model.Produto;

public class App {
    public static void main(String[] args) throws Exception {

        TelaEntrada telaInicial = new TelaEntrada();
        telaInicial.setVisible(true);

        // Connection conexao = new Conexao().getConnection();
        // ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        // ArrayList<Produto> all = produtoDAO.selectAllProdutos();
        // for (Produto prod : all) {
        //     System.out.println(prod.toString());
        // }
        
    }
}
