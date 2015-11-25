package bean.Cliente;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import domain.Cliente;

@ManagedBean(name = "MBNovoCliente")
@ViewScoped
public class NovoClienteBean implements Serializable {

	private transient Cliente cliente = new Cliente();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void novoCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect("lista.xhtml");
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
}