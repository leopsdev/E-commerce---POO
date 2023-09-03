import java.time.LocalDateTime;
import java.util.Scanner;

public class Pagamento {
    private TipoPagamento tipo;
    private String info;
    private StatusPagamento status_pagamento;
    private LocalDateTime data;
    private Scanner scan = new Scanner(System.in);

    public Pagamento(TipoPagamento tipo, String info) {
        this.tipo = tipo;
        this.info = info;
        this.status_pagamento = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
    }
    
    public TipoPagamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public StatusPagamento getStatus() {
        return status_pagamento;
    }
    public void setStatus(StatusPagamento status) {
        this.status_pagamento = status;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    /*public Pedido processarPagamento(Pedido pedido){
        if(pedido != null){
            if(tipo == TipoPagamento.CARTAO_CREDITO || tipo == TipoPagamento.CARTAO_DEBITO || tipo == TipoPagamento.PIX || tipo == TipoPagamento.TRANSFERENCIA){
                status_pagamento = StatusPagamento.CONCLUIDO;
                pedido.setStatus(StatusPedido.CONCLUIDO);
            }
            else{
                status_pagamento = StatusPagamento.CANCELADO;
                System.out.println("Não possuímos essa forma de pagamento. Selecione outra ou cancele o pedido.");
                // Se ele selecionar outra e der certo, okay. Se não,cancela o pedido e fala: pedido cancelado.
                // Talvez implementar uma validação para o pagamento.
                pedido.setStatus(StatusPedido.CANCELADO);
                System.out.println("Pedido cancelado.");
            }
            return pedido;
        }
        else{
            System.out.println("Seu pedido não foi realizado.");
            
        }
        return null;
    }*/
    public Pedido processarPagamento(Pedido pedido){
        if(pedido != null){
            if(this.tipo == TipoPagamento.CARTAO_CREDITO || this.tipo == TipoPagamento.CARTAO_DEBITO || this.tipo == TipoPagamento.PIX || this.tipo == TipoPagamento.TRANSFERENCIA){
                status_pagamento = StatusPagamento.CONCLUIDO;
                pedido.setStatus(StatusPedido.CONCLUIDO);
            }
            else{
                int nova_forma = this.novaFormaDePagamento();
                if(nova_forma == 999 || nova_forma == 5){
                    this.status_pagamento = StatusPagamento.CANCELADO;
                    pedido.setStatus(StatusPedido.CANCELADO);
                    System.out.println("Cancelamento efetuado ao tentar realizar pagamento. Seu pedido foi cancelado com sucesso.");
                }
                else{
                    status_pagamento = StatusPagamento.CONCLUIDO;
                    pedido.setStatus(StatusPedido.CONCLUIDO);
                }
            }
            return pedido;
        }
        else{
            System.out.println("Seu pedido não foi realizado.");
            
        }
        return null;
    }
    private int novaFormaDePagamento(){
        int nova_forma;
        System.out.println("Não possuímos essa forma de pagamento. Selecione outra ou cancele o pedido.");
        System.out.println("Digite [1] para cancelar o pedido;\nDigite [2] para selecionar outra forma de pagamento;");
        int escolha = scan.nextInt();
        while(escolha != 1 & escolha != 2){
            System.out.println("Opção inválida. ");
            System.out.println("Digite [1] para cancelar o pedido;\nDigite [2] para selecionar outra forma de pagamento;");
            escolha = scan.nextInt();
        }
        if(escolha == 2){
            System.out.println("=-=-=-=-=-=-=-=-=-=-FORMAS DE PAGAMENTO=-=-=-=-=-=-=-=-=-=-");
            System.out.println("[1] - CRÉDITO.\n[2] - DÉBITO\n[3] - PIX\n[4] - TRANSFERÊNCIA\n\n\n");
            System.out.println("Selecione a nova forma de pagamento:");
            nova_forma = scan.nextInt();
            while(nova_forma != 1 & nova_forma != 2 & nova_forma != 3 & nova_forma != 4){
                System.out.println("Opção inválida. Digite uma das opções mostradas.\n");
                System.out.println("[1] - CRÉDITO.\n[2] - DÉBITO\n[3] - PIX\n[4] - TRANSFERÊNCIA\n[5] - CANCELAR\n\n");
                System.out.println("Selecione a nova forma de pagamento:");
                nova_forma = scan.nextInt();
            }
            switch(nova_forma){
                case 1:
                    this.tipo = TipoPagamento.CARTAO_CREDITO;
                    break;
                case 2:
                    this.tipo = TipoPagamento.CARTAO_DEBITO;
                    break;
                case 3:
                    this.tipo = TipoPagamento.PIX;
                    break;
                case 4:
                    this.tipo = TipoPagamento.TRANSFERENCIA;
                    break;
            }
        }
        else{
            nova_forma = 999;
        }
        return nova_forma;
    }
}

enum TipoPagamento{
    CARTAO_CREDITO, CARTAO_DEBITO, PIX, TRANSFERENCIA;
}

enum StatusPagamento{
    PENDENTE, PROCESSANDO, CONCLUIDO, CANCELADO;
}
