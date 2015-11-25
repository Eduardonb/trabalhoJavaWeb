package bean.Servico;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import dao.ClienteDAO;
import dao.ServicoDAO;
import domain.Cliente;
import domain.Servico;

@ManagedBean(name = "MBListaServico")
@ViewScoped
public class ListaServicoBean implements Serializable {

	private ListDataModel<Servico> servicos;
	private transient Cliente cliente;

	public ListDataModel<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(ListDataModel<Servico> servicos) {
		this.servicos = servicos;
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
			int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscar(id);
			
			ServicoDAO servicoDAO = new ServicoDAO();
			ArrayList<Servico> lista = servicoDAO.buscarTodosServicosDeUmCliente(cliente);
			servicos = new ListDataModel<Servico>(lista);
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}
}