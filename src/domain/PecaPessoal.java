package domain;

public class PecaPessoal {
	
	private int id;
	private Servico servico;
	private String descricao;
	private boolean delicada;
	private int quantidade;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isDelicada() {
		return delicada;
	}
	
	public void setDelicada(boolean delicada) {
		this.delicada = delicada;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getId() {
		return id;
	}
	
}
