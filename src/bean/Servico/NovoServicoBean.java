package bean.Servico;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dao.ClienteDAO;
import dao.ServicoDAO;
import domain.Cliente;
import domain.Servico;

@ManagedBean(name = "MBNovoServico")
@ViewScoped
public class NovoServicoBean implements Serializable {

	private transient Servico servico = new Servico();
	private int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
	private transient ClienteDAO clienteDAO = new ClienteDAO();
	private transient Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public void novoServico() {
		System.out.println("Aki");
		try {
			cliente = clienteDAO.buscar(id);
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.salvar(servico, cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect("lista.xhtml");
			
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
}