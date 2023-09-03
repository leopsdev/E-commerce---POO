import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Vendedor extends Usuario{
    private long cnpj;
    private String nome_empresa;
    private String descricao_empresa;
    private HashMap<Produto, Integer> estoque = new HashMap<>();
    private GerenciadorDeEstoque ger_est = new GerenciadorDeEstoque(estoque);
    private ArrayList<Pedido> lista_pedidos = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Vendedor(String nome, String endereco, String email, long cnpj, String nome_empresa,
            String descricao_empresa) {
        super(nome, endereco, email);
        this.cnpj = cnpj;
        this.nome_empresa = nome_empresa;
        this.descricao_empresa = descricao_empresa;
    }
    public long getCnpj() {
        return cnpj;
    }
    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
    public String getNome_empresa() {
        return nome_empresa;
    }
    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }
    public String getDescricao_empresa() {
        return descricao_empresa;
    }
    public void setDescricao_empresa(String descricao_empresa) {
        this.descricao_empresa = descricao_empresa;
    }
    public GerenciadorDeEstoque getEstoque(){
        return this.ger_est;
    }
    public Produto cadastrarProduto1(){
        System.out.println("Digite o nome do produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite o preço do produto: ");
        double preco = scan.nextDouble();
        System.out.println("Digite a descrição do produto: ");
        String descricao = scan.nextLine();
        System.out.println("Digite a categoria do produto: ");
        String categoria = scan.nextLine();
        Produto produto = new Produto(nome, descricao, preco, categoria);
        return produto;
    }
    public void cadastrarProduto2(Produto produto){
        System.out.println("Digite o nome do produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite o preço do produto: ");
        double preco = scan.nextDouble();
        System.out.println("Digite a descrição do produto: ");
        String descricao = scan.nextLine();
        System.out.println("Digite a categoria do produto: ");
        String categoria = scan.nextLine();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        produto.setDescricao(descricao);
    }
    public void adicionarListaDePedido(Pedido pedido){
        lista_pedidos.add(lista_pedidos.size(), pedido);
    }

    public Pedido processarPedido(Pedido pedido){
        int compra_valida = 0;
        pedido.setStatus(StatusPedido.PROCESSANDO);
        HashMap<Produto, Integer> falta_produtos = new HashMap<>();
        for(Map.Entry<Produto, Integer> par: pedido.getCarrinho().getLista_produtos().entrySet()){
            if(ger_est.verificaDisponibilidade(par.getKey(), par.getValue())){
                compra_valida++;
            }
            else{
                Produto produto_nao_comprado = par.getKey();
                int quantidade_faltante = par.getValue() - ger_est.getEstoque().get(produto_nao_comprado);
                falta_produtos.put(produto_nao_comprado, quantidade_faltante);
            }
        }
        if(compra_valida == pedido.getCarrinho().getLista_produtos().size()){
            pedido.concluirPedido();
            return pedido;
            // Mais alguma coisa
        }
        else{
            pedido.cancelarPedido();
            mostraProdutosFaltantes(falta_produtos);
            modificacaoPedido(pedido);
        }
        return null; // Só retorna o pedido quando este for REALIZADO, isto é, se passar pela função concluir pedido. Do contrário, retorna null.
    }
    public void realizaVenda(Pedido pedido){
        if(pedido != null){
            if(pedido.getStatus()==StatusPedido.CONCLUIDO){
                ger_est.atualizaEstoque(pedido);
            }
            else{
                System.out.println("Pagamento não realizado. Até a próxima!");
            }
        }
    }

    private void mostraProdutosFaltantes(HashMap<Produto, Integer> falta_produtos){
        System.out.println("O pedido foi cancelado em decorrência das seguintes faltas no estoque: ");
        for(Map.Entry<Produto, Integer> falta: falta_produtos.entrySet()){
                System.out.printf("Produto: %s\nQuantidade em falta: %d\n\n", falta.getKey(), falta.getValue());
            }
        System.out.println("Lamentamos pelo contratempo. Modifique sua compra ou cancele-a:");
    }

    private void modificacaoPedido(Pedido pedido){
        System.out.println("Digite [1] para modificar seu carrinho de compras;\nDigite [2] para cancelar o pedido;");
        int decisao = scan.nextInt();
        while(decisao != 1 & decisao != 2){
            System.out.println("Opção inválida. Digite uma das opções listadas. ");
            System.out.println("Digite [1] para modificar seu carrinho de compras;\nDigite [2] para cancelar o pedido;");
            decisao = scan.nextInt();
        }
        if(decisao == 1){
            pedido.getCarrinho().modificarCarrinho(pedido, this);
        }
        else{
            System.out.println("Cliente optou por não mudar o carrinho e cancelar a compra. Até a próxima.");
        }
    }
}
