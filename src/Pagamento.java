import java.time.LocalDateTime;

public class Pagamento {
    private TipoPagamento tipo;
    private String info;
    private StatusPagamento status;
    private LocalDateTime data;

    public Pagamento(TipoPagamento tipo, String info) {
        this.tipo = tipo;
        this.info = info;
        this.status = StatusPagamento.PENDENTE;
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
        return status;
    }
    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void processar(){
        if(tipo == TipoPagamento.CARTAO_CREDITO || tipo == TipoPagamento.CARTAO_DEBITO || tipo == TipoPagamento.PIX || tipo == TipoPagamento.TRASNFERENCIA){
            status = StatusPagamento.CONCLUIDO;
        }
        else{
            status = StatusPagamento.CANCELADO;
        }
    }
}

enum TipoPagamento{
    CARTAO_CREDITO, CARTAO_DEBITO, PIX, TRASNFERENCIA;
}

enum StatusPagamento{
    PENDENTE, PROCESSANDO, CONCLUIDO, CANCELADO;
}
