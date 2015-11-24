package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.ClienteDAO;
import domain.Cliente;

@ManagedBean(name = "MBListaCliente")
@ViewScoped
public class ListaClienteBean {

	private Cliente cliente;
	private ListDataModel<Cliente> clientes;

	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@PostConstruct
	public void preparaListagemCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			ArrayList<Cliente> lista = clienteDAO.buscarTodosClientes();
			clientes = new ListDataModel<Cliente>(lista);
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}

	public void preparaNovoCliente() {
		cliente = new Cliente();
	}
	
	public void novoCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}
}