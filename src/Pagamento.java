import java.time.LocalDateTime;

public class Pagamento {
    private TipoPagamento tipo;
    private String info;
    private StatusPagamento status_pagamento;
    private LocalDateTime data;

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

    public void processarPagamento(Pedido pedido){
        if(tipo == TipoPagamento.CARTAO_CREDITO || tipo == TipoPagamento.CARTAO_DEBITO || tipo == TipoPagamento.PIX || tipo == TipoPagamento.TRANSFERENCIA){
            status_pagamento = StatusPagamento.CONCLUIDO;
        }
        else{
            status_pagamento = StatusPagamento.CANCELADO;
        }
        if(status_pagamento == StatusPagamento.CONCLUIDO){
            pedido.setStatus(StatusPedido.PROCESSANDO);
            pedido.concluirPedido();
            // Atualizar estoque;
        }
    }
}

enum TipoPagamento{
    CARTAO_CREDITO, CARTAO_DEBITO, PIX, TRANSFERENCIA;
}

enum StatusPagamento{
    PENDENTE, PROCESSANDO, CONCLUIDO, CANCELADO;
}
