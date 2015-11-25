package bean.Cliente;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import domain.Cliente;

@ManagedBean(name = "MBEditarCliente")
@ViewScoped
public class EditarClienteBean implements Serializable {

	private transient Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@PostConstruct
	public void carregaCliente() {
		try {
			int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscar(id);
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}
	
	public void editarCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.atualizar(cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect("lista.xhtml");
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
}