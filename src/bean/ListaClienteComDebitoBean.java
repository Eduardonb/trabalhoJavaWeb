package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.ClienteDAO;
import domain.Cliente;

@ManagedBean(name = "MBListaClienteComDebito")
@ViewScoped
public class ListaClienteComDebitoBean {

	private ListDataModel<Cliente> debitos;
	
	@PostConstruct
	public void preparaListagemDebitos()
	{
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			ArrayList<Cliente> lista = clienteDAO.buscarTodosClientesComDebito();
			debitos = new ListDataModel<Cliente>(lista);
		} catch(SQLException erro) {
			erro.printStackTrace();
		}
	}

	public ListDataModel<Cliente> getDebitos() {
		return debitos;
	}

	public void setDebitos(ListDataModel<Cliente> debitos) {
		this.debitos = debitos;
	}
}
