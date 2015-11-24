package domain;

public class PecaCMB {
	
	private int id;
	private Servico servico;
	private String descricao;
	private float peso;
	
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
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public int getId() {
		return id;
	}
	
}
