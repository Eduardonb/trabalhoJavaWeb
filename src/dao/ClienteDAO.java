package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Cliente;
import factory.Conexao;

public class ClienteDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void salvar(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cliente ");
		sql.append("(nome, cpf, telefone) ");
		sql.append("VALUES (?, ?, ?) ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getNome());
		comando.setString(2, cliente.getCpf());
		comando.setString(3, cliente.getTelefone());
		
		comando.executeUpdate();
	}
	
	public void excluir(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cliente ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, cliente.getId());
		
		comando.executeUpdate();
	}
	
	public void atualizar(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cliente ");
		sql.append("SET nome = ?, ");
		sql.append("cpf = ?, ");
		sql.append("telefone = ? ");
		sql.append("WHERE id = ?; ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getNome());
		comando.setString(2, cliente.getCpf());
		comando.setString(3, cliente.getTelefone());
		comando.setInt(4, cliente.getId());
		
		comando.executeUpdate();
	}
	
	public Cliente buscar(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, cpf, telefone ");
		sql.append("FROM cliente ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, id);
		
		ResultSet resultado = comando.executeQuery();
		
		Cliente retorno = null;
		
		if (resultado.next()) {
			retorno = new Cliente();
			retorno.setId(resultado.getInt("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setCpf(resultado.getString("cpf"));
			retorno.setTelefone(resultado.getString("telefone"));
		}
		
		return retorno;
	}
	
	public ArrayList<Cliente> buscarTodosClientes() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, cpf, telefone ");
		sql.append("FROM cliente");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while (resultado.next()) {
			Cliente cliente = new Cliente();
			cliente.setId(resultado.getInt("id"));
			cliente.setNome(resultado.getString("nome"));
			cliente.setCpf(resultado.getString("cpf"));
			cliente.setTelefone(resultado.getString("telefone"));
			
			lista.add(cliente);
		}
		
		return lista;
	}
	
	public ArrayList<Cliente> buscarTodosClientesComDebito() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cliente.id, cliente.nome, cliente.cpf, cliente.telefone ");
		sql.append("FROM cliente ");
		sql.append("LEFT JOIN servico ");
		sql.append("ON servico.cliente_id = cliente.id ");
		sql.append("WHERE servico.pago <> 1 ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while (resultado.next()) {
			Cliente cliente = new Cliente();
			cliente.setId(resultado.getInt("id"));
			cliente.setNome(resultado.getString("nome"));
			cliente.setCpf(resultado.getString("cpf"));
			cliente.setTelefone(resultado.getString("telefone"));
			
			lista.add(cliente);
		}
		
		return lista;
	}
}
