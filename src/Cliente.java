import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private int id_cliente;
    private String nome;
    private String endereco;     // Criar uma classe ou um método pra coletar direito os dados do endereço
    private String info_pagamento;
    private List<Produto> listaDesejos = new ArrayList<>();
    private List<Pedido> historicoCompras = new ArrayList<>();
    private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
    private static int proximoID = 1;

    Scanner scan = new Scanner(System.in);

    public Cliente(String nome, String endereco, String info_pagamento, List<Produto> listaDesejos) {
        this.nome = nome;
        this.endereco = endereco;
        this.info_pagamento = info_pagamento;
        this.listaDesejos = listaDesejos;
        this.id_cliente = proximoID;
        proximoID++;
    }

    public int getId_cliente() {
        return id_cliente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getInfo_pagamento() {
        return info_pagamento;
    }
    public void setInfo_pagamento(String info_pagamento) {
        this.info_pagamento = info_pagamento;
    }
    public List<Produto> getListaDesejos() {
        return listaDesejos;
    }
    public void setListaDesejos(List<Produto> listaDesejos) {
        this.listaDesejos = listaDesejos;
    }
    public List<Pedido> getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(List<Pedido> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public void adicionarAoCarrinho(Produto produto){
        System.out.println("Digite quantas unidades deseja comprar: ");
        int quantidade = scan.nextInt();
        this.carrinho.adicionarProduto(produto, quantidade);
        
    }
    public void realizarCompra(Pagamento pagamento){
        Pedido pedido = new Pedido(this, carrinho);
        pedido.mostrarPedido(pedido);
        pagamento.processarPagamento(pedido);
        historicoCompras.add(pedido);
        carrinho.limparCarrinho();
        // Diminuir estoque
    }

    public void adicionarListaDeDesejos(Produto produto){
        this.listaDesejos.add(produto);
    }

    public void adicionarAoHistorico(Pedido pedido){
        this.historicoCompras.add(pedido);
    }
    
}
