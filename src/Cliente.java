import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Usuario{
    private long cpf;
    private String info_pagamento;
    private List<Produto> listaDesejos = new ArrayList<>();
    private List<Pedido> historicoCompras = new ArrayList<>();
    private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

    Scanner scan = new Scanner(System.in);

    public Cliente(String nome, String endereco, String email, long cpf, String info_pagamento) {
        super(nome, endereco, email);
        this.info_pagamento = info_pagamento;
        this.cpf = cpf;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
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
    public List<Pedido> getHistoricoCompras() {
        return historicoCompras;
    }
    public CarrinhoDeCompras getCarrinhoDeCompras(){
        return this.carrinho;
    }

    public void adicionarAoCarrinho(Produto produto, Vendedor vendedor){
        System.out.println("Digite quantas unidades deseja comprar: ");
        int quantidade = scan.nextInt();
        boolean verificaDisponibilidade = vendedor.getEstoque().verificaDisponibilidade(produto, quantidade);
        int escolha1 = 0;
        while(verificaDisponibilidade == false){
            System.out.println("Você selecionou uma quantidade que não possuímos em estoque. Selecione uma nova quantidade ou cancele a adição.");
            System.out.println("[1] - Para modificar a quantidade adicionada\n[2] - Para cancelar a adição");
            System.out.println("Selecione uma das opções acima: ");
            escolha1 = scan.nextInt();
            while(escolha1 != 1 & escolha1 != 2){
                System.out.println("Opção inválida. Selecione uma das opções fornecidas: ");
                escolha1 = scan.nextInt();             
            }
            if(escolha1 == 1){
                System.out.println("Digite quantas unidades deste produto deseja comprar: ");
                quantidade = scan.nextInt();
                verificaDisponibilidade = vendedor.getEstoque().verificaDisponibilidade(produto, quantidade);
            }
            else{
                break;
            }
        }
        if(verificaDisponibilidade){
            this.carrinho.adicionarProduto(produto, quantidade);
        }
        else{
            System.out.println("Adição cancelada.");
        }
    }

    public void montarCarrinho(){
        int escolha1 = 0;
        int escolha2 = 0;
        int id_removido;
        int id_alterado;
        int quant_alterada;

        while(escolha1 != 2){
            System.out.println("[1] - Adicionar produto\n[2] - Remover produto\n[3] - Alterar quantidade de um produto\n\n");
            System.out.println("Digite uma das opções acima: ");
            escolha2 = scan.nextInt();
            while(escolha2 != 1 & escolha2 != 2 & escolha2 != 3){
                System.out.println("Opção inválida. Digite uma das opções fornecidas: ");
                escolha2 = scan.nextInt();
            }
            switch(escolha2){
                case 1:
                    System.out.println("Função em desenvolvimento."); // O vendedor em questão deve mostrar quais os produtos que ele tem.
                    break;
                case 2:
                    System.out.println("Digite o id do produto que deseja remover: ");
                    id_removido = scan.nextInt();
                    Produto produto_removido = this.carrinho.encontraProdutoNoCarrinho(id_removido);
                    this.carrinho.removerProduto(produto_removido);
                    break;
                case 3:
                    System.out.println("Digite o id do produto cuja quantidade em seu carrinho deseja alterar: ");
                    id_alterado = scan.nextInt();
                    System.out.println("Digite a nova quantidade deste produto que deseja em seu carrinho: ");
                    quant_alterada = scan.nextInt();

                    Produto produto_quant_alterada = this.carrinho.encontraProdutoNoCarrinho(id_alterado);
                    boolean verificaDisponibilidade = produto_quant_alterada.getVendedor().getEstoque().verificaDisponibilidade(produto_quant_alterada, quant_alterada);
                    while(verificaDisponibilidade == false){
                        System.out.println("Não temos essa quantidade em estoque. Digite outra quantidade: ");
                        quant_alterada = scan.nextInt();
                        verificaDisponibilidade = produto_quant_alterada.getVendedor().getEstoque().verificaDisponibilidade(produto_quant_alterada, quant_alterada);
                    }
                    this.carrinho.getLista_produtos().put(produto_quant_alterada, quant_alterada);
                    break;
            }
            System.out.println("Deseja continuar modificando o carrinho? [1] - Sim; [2] - Não");
            escolha1 = scan.nextInt();
        }
    }

    public Pedido fazerPedido(){
        Pedido pedido = new Pedido(this);
        pedido.mostrarPedido();

        return pedido;
    }
    public void realizarCompra(Pagamento pagamento, Vendedor vendedor){
        Pedido pedido = fazerPedido();    
        Pedido pedido_processado = vendedor.processarPedido(pedido);
        Pedido pedido_pago = pagamento.processarPagamento(pedido_processado);
        
        // Quando o cliente for fazer o pagamento, pergunto quais os dados do pagamento desse cliente pra poder criar a variável do tipo Pagam.
        
        boolean verificacao = pedido_pago.verificacaoCompra();
        if(verificacao){
            historicoCompras.add(pedido);
            carrinho.limparCarrinho();
            vendedor.realizaVenda(pedido_pago);
        }
        else{
            System.out.println("Compra não realizada.");            
        } 
    }

    public void adicionarListaDeDesejos(Produto produto){
        this.listaDesejos.add(produto);
    }

    public void adicionarAoHistorico(Pedido pedido){
        this.historicoCompras.add(pedido);
    }
    public boolean temHistorico(){
        return !historicoCompras.isEmpty();
    }
    
}
