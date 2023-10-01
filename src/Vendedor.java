import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Vendedor extends Usuario{
    private long cnpj;
    private String descricao_empresa;
    private HashMap<Produto, Integer> estoque = new HashMap<>();
    private GerenciadorDeEstoque ger_est = new GerenciadorDeEstoque(estoque);
    private ArrayList<Pedido> lista_pedidos = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public Vendedor(String nome, String endereco, String email, String senha, long cnpj, String descricao_empresa) {
        super(nome, endereco, email, senha);
        this.cnpj = cnpj;
        this.descricao_empresa = descricao_empresa;
    }
    public long getCnpj() {
        return cnpj;
    }
    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
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
        System.out.println("Digite a quantidade que deseja adicionar: ");
        int quantidade = scan.nextInt();
        Produto produto = new Produto(nome, descricao, preco, categoria, this);
        ger_est.adicionarProdutoNoEstoque(produto, quantidade);

        return produto;
    }
    public void modificarProduto(Produto produto){ 
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
        pedido.setStatus(StatusPedido.REALIZADO);
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
