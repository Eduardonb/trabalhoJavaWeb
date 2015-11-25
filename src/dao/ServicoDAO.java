package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Cliente;
import domain.Servico;
import factory.Conexao;

public class ServicoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ClienteDAO clienteDAO = new ClienteDAO();
	
	public void salvar(Servico servico, Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO servico ");
		sql.append("(cliente_id, data, pago, valor) ");
		sql.append("VALUES (?, ?, ?, ?) ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, cliente.getId());
		comando.setDate(2, servico.getData());
		comando.setBoolean(3, servico.isPago());
		comando.setFloat(4, servico.getValor());
		
		comando.executeUpdate();
	}
	
	public void excluir(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM servico ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, servico.getId());
		
		comando.executeUpdate();
	}
	
	public void atualizar(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE servico ");
		sql.append("SET pago = ? ");
		sql.append("SET data = ? ");
		sql.append("SET valor = ? ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setBoolean(1, servico.isPago());
		comando.setDate(2, servico.getData());
		comando.setFloat(3, servico.getValor());
		comando.setInt(4, servico.getId());
		
		comando.executeUpdate();
	}
	
	public Servico buscar(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, data, cliente_id, pago, valor ");
		sql.append("FROM servico ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, id);
		
		ResultSet resultado = comando.executeQuery();
		
		Servico retorno = null;
		
		if (resultado.next()) {
			retorno = new Servico();
			retorno.setId(resultado.getInt("id"));
			retorno.setData(resultado.getDate("data"));
			retorno.setCliente(clienteDAO.buscar(resultado.getInt("cliente_id")));
			retorno.setPago(resultado.getBoolean("pago"));
			retorno.setValor(resultado.getFloat("valor"));
		}
		
		return retorno;
	}	
	
	public ArrayList<Servico> buscarTodosServicosDeUmCliente(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, ");
		sql.append("cliente_id, pago, valor, ");
		sql.append("CASE WHEN data !='0000-00-00' THEN data ELSE NULL END as data ");
		sql.append("FROM servico ");
		sql.append("WHERE cliente_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, cliente.getId());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Servico> lista = new ArrayList<Servico>();
		
		while (resultado.next()) {
			Servico servico = new Servico();
			servico.setId(resultado.getInt("id"));
			servico.setData(resultado.getDate("data"));
			servico.setCliente(clienteDAO.buscar(resultado.getInt("cliente_id")));
			servico.setPago(resultado.getBoolean("pago"));
			servico.setValor(resultado.getFloat("valor"));
			
			lista.add(servico);
		}
		
		return lista;
	}
	
	public ArrayList<Servico> buscarTodosServicosEmAbertoDeUmCliente(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, data, cliente_id, pago, valor ");
		sql.append("FROM servico ");
		sql.append("WHERE cliente_id = ? ");
		sql.append("AND pago = TRUE ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Servico> lista = new ArrayList<Servico>();
		
		while (resultado.next()) {
			Servico servico = new Servico();
			servico.setId(resultado.getInt("id"));
			servico.setData(resultado.getDate("data"));
			servico.setCliente(clienteDAO.buscar(resultado.getInt("cliente_id")));
			servico.setPago(resultado.getBoolean("pago"));
			servico.setValor(resultado.getFloat("valor"));
			
			lista.add(servico);
		}
		
		return lista;
	}
	
	public void excluiTodosServicoDeUmCliente(Cliente cliente, Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM servico");
		sql.append("WHERE servico_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, cliente.getId());
		
		comando.executeUpdate();
	}
}
