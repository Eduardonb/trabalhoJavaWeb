package bean.Cliente;

import java.io.Serializable;
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
public class ListaClienteBean implements Serializable {

	private ListDataModel<Cliente> clientes;

	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
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
}