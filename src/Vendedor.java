import java.util.ArrayList;
import java.util.HashMap;
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
    public Produto cadastrarProduto(){
        System.out.println("Digite o nome do produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite o preço do produto: ");
        double preco = scan.nextDouble();
        System.out.println("Digite a descrição do produto: ");
        String descricao = scan.nextLine();
        System.out.println("Digite a categoria do produto: ");
        String categoria = scan.nextLine();
        Produto produto = new Produto(nome, descricao, preco, categoria, this);
        return produto;
    }
    public void modificarProduto(Produto produto){ //* */
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
        pedido.setStatus(StatusPedido.PROCESSANDO);
        this.adicionarListaDePedido(pedido);
        pedido.realizarPedido();
        return pedido;
    }

    public void modificarPedido(Pedido pedido){
        int tem_certeza = 0;
        System.out.println("Deseja finalizar o pedido? [1] - Sim; [2] - Não, quero mudar");
        tem_certeza = scan.nextInt();
        while(tem_certeza != 1 & tem_certeza != 2){
            System.out.println("Opção inválida. Selecione uma das opções fornecidas: ");
            tem_certeza = scan.nextInt();
        }
        if(tem_certeza == 1){
            pedido.realizarPedido();    //* */
            System.out.println("Pedido realizado. Hora de efetuar o pagamento.");
        }
        else{
            pedido.getCliente().montarCarrinho();
        }
    }

    public int selecionarFormaPagamento(){
        int forma;
        System.out.println("=-=-=-=-=-=-=-=-=-=-FORMAS DE PAGAMENTO=-=-=-=-=-=-=-=-=-=-");
        System.out.println("[1] - CRÉDITO.\n[2] - DÉBITO\n[3] - PIX\n[4] - TRANSFERÊNCIA\n\n\n");
        System.out.println("Selecione a nova forma de pagamento:");
        forma = scan.nextInt();
        while(forma != 1 & forma != 2 & forma != 3 & forma != 4){
                System.out.println("Opção inválida. Digite uma das opções mostradas.\n");
                System.out.println("[1] - CRÉDITO.\n[2] - DÉBITO\n[3] - PIX\n[4] - TRANSFERÊNCIA\n[5] - CANCELAR\n\n");
                System.out.println("Selecione a nova forma de pagamento:");
                forma = scan.nextInt();
            }
            if(forma == 5){
                System.out.println("Pagamento cancelado. Pedido cancelado.");
                return 999;
                
            }
            else{
                return forma;
            }
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
}
