public class App {
    public static void main(String[] args) throws Exception {
        
        Vendedor vend1 = new Vendedor("Caixa.com","aaa,1502","aaa@gmail.com", "1234", 1234,"Loja de caixas");
        
        Produto prod1 = vend1.cadastrarProduto();

        Cliente cliente1 = new Cliente("Leonardo","Triangulo das Bermudas","leopereiraesilca@gmail.com", "12345", 40020322);

        cliente1.adicionarListaDeDesejos(prod1);

        boolean verificacao = cliente1.getCarrinhoDeCompras().adicionarAoCarrinho(prod1);
        if(verificacao){
            cliente1.realizarCompra();
        }
        
        

          
    }
}
