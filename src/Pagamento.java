import java.time.LocalDateTime;
import java.util.Scanner;

public class Pagamento {
    private TipoPagamento tipo;
    private StatusPagamento status_pagamento;
    private LocalDateTime data;
    private Scanner scan = new Scanner(System.in);

    public Pagamento(TipoPagamento tipo) {
        this.tipo = tipo;
        this.status_pagamento = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
    }
    
    public TipoPagamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
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
    public TipoPagamento selecionarFormaPagamento(){
        int forma;
        TipoPagamento tipo = TipoPagamento.INDEFINIDO;
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
            switch(forma){
                case 1:
                    tipo = TipoPagamento.CARTAO_CREDITO;
                    break;
                case 2:
                    tipo = TipoPagamento.CARTAO_DEBITO;
                    break;
                case 3:
                    tipo = TipoPagamento.PIX;
                    break;
                case 4:
                    tipo = TipoPagamento.TRANSFERENCIA;
                    break;
            }
            if(forma == 5){
                return null;
            }
            else{
                return tipo;
            }
    }
    public Pedido processarPagamento(Pedido pedido){
        TipoPagamento tipo = selecionarFormaPagamento();
        Pagamento pagamento = new Pagamento(tipo);
        if(pedido != null){
            pagamento.setStatus(StatusPagamento.CONCLUIDO);
            pedido.setStatus(StatusPedido.CONCLUIDO);
            return pedido;
        }
        else{
            System.out.println("Pagamento cancelado. Pedido cancelado.");
            
        }
        return null;
    }
}

enum TipoPagamento{
    CARTAO_CREDITO, CARTAO_DEBITO, PIX, TRANSFERENCIA, INDEFINIDO;
}

enum StatusPagamento{
    PENDENTE, PROCESSANDO, CONCLUIDO, CANCELADO;
}
