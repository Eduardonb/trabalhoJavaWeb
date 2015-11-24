package domain;

import java.sql.Date;

public class Servico {
	
	private int id;
	private Cliente cliente;
	private boolean pago;
	private float valor;
	private Date data;  
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isPago() {
		return pago;
	}
	
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public int getId() {
		return id;
	}
	
	
}
