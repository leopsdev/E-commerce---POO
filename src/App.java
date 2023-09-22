public class App {
    public static void main(String[] args) throws Exception {
        
        Vendedor vend1 = new Vendedor("Caixa.com","caixas.com","caixacom@gmail.com",01,"Caixas.com","Loja de caixas");
        
        Produto prod1 = vend1.cadastrarProduto();

        Cliente cliente1 = new Cliente("Leonardo","Triangulo das Bermudas","leopereiraesilca@gmail.com",40020322,"null");

        cliente1.adicionarListaDeDesejos(prod1);

        cliente1.adicionarAoCarrinho(prod1, vend1);
        
        Pedido ped1 = cliente1.fazerPedido();
          
    }
}
