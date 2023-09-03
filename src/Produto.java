
public class Produto{
  private int id_produto;
  private String nome;
  private String descricao;
  private double preco;
  private String categoria;
  private int quant_estoque;    // Importante na hora de mapear os objetos através de um csv.
  private static int proximoID = 1;

  public Produto(final String nome, final String descricao, final double preco, final String categoria){
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.categoria = categoria;
    this.id_produto = proximoID;
    proximoID++;
  }
  public int getId_produto() {
  	return id_produto;
  }
  public void setId_produto(final int id_produto) {
  	this.id_produto = id_produto;
  }
  public String getNome() {
  	return nome;
  }
  public void setNome(final String nome) {
  	this.nome = nome;
  }
  public String getDescricao() {
  	return descricao;
  }
  public void setDescricao(final String descricao) {
  	this.descricao = descricao;
  }
  public double getPreco() {
  	return preco;
  }
  public void setPreco(final double preco) {
  	this.preco = preco;
  }
  public String getCategoria() {
  	return categoria;
  }
  public void setCategoria(final String categoria) {
  	this.categoria = categoria;
  }
  public int getQuant_estoque() {
  	return quant_estoque;
  }
  public void setQuant_estoque(final int quant_estoque) {
  	this.quant_estoque = quant_estoque;
  }
}