package dao;

public class PedidoAux {
    private int id_produto;
    private long cnpj_vendedor;
    private int quant_selecionada;
    private double preco_produto;
    private int id_cliente;
    private String email_cliente;
    private String status;
    
    public PedidoAux(int id_produto, long cnpj_vendedor, int quant_selecionada, double preco_produto, int id_cliente,
            String email_cliente, String status) {
        this.id_produto = id_produto;
        this.cnpj_vendedor = cnpj_vendedor;
        this.quant_selecionada = quant_selecionada;
        this.preco_produto = preco_produto;
        this.id_cliente = id_cliente;
        this.email_cliente = email_cliente;
        this.status = status;
    }
    
    public int getId_produto() {
        return id_produto;
    }
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
    public long getCnpj_vendedor() {
        return cnpj_vendedor;
    }
    public void setCnpj_vendedor(long cnpj_vendedor) {
        this.cnpj_vendedor = cnpj_vendedor;
    }
    public int getQuant_selecionada() {
        return quant_selecionada;
    }
    public void setQuant_selecionada(int quant_selecionada) {
        this.quant_selecionada = quant_selecionada;
    }
    public double getPreco_produto() {
        return preco_produto;
    }
    public void setPreco_produto(double preco_produto) {
        this.preco_produto = preco_produto;
    }
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getEmail_cliente() {
        return email_cliente;
    }
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
