package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.PecaCMB;
import domain.Servico;
import factory.Conexao;

public class PecaCMBDAO {
	
	private ServicoDAO servicoDAO = new ServicoDAO();
	
	public void salvar(PecaCMB pecaCMB, Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO pecacmb ");
		sql.append("(servico_id, descricao, peso ) ");
		sql.append("VALUES (?, ?, ?) ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, servico.getId());
		comando.setString(2, pecaCMB.getDescricao());
		comando.setFloat(3, pecaCMB.getPeso());
		
		comando.executeUpdate();
	}
	
	public void excluir(PecaCMB pecaCMB) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM pecacmb");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, pecaCMB.getId());
		
		comando.executeUpdate();
	}
	
	public void atualizar(PecaCMB pecaCMB) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE pecacmb ");
		sql.append("SET descricao = ? ");
		sql.append("SET peso = ? ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, pecaCMB.getDescricao());
		comando.setFloat(2, pecaCMB.getPeso());
		comando.setInt(4, pecaCMB.getId());
		
		comando.executeUpdate();
	}
	
	public PecaCMB buscar(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, servico_id, descricao, peso ");
		sql.append("FROM pecacmb ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, id);
		
		ResultSet resultado = comando.executeQuery();
		
		PecaCMB retorno = null;
		
		if (resultado.next()) {
			retorno = new PecaCMB();
			retorno.setId(resultado.getInt("id"));
			retorno.setServico(servicoDAO.buscar(resultado.getInt("servico_id")));
			retorno.setDescricao(resultado.getString("descricao"));
			retorno.setPeso(resultado.getFloat("peso"));
		}
		
		return retorno;
	}	
	
	public ArrayList<PecaCMB> buscarTodasPecasDeUmServico(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, servico_id, descricao, peso ");
		sql.append("FROM pecacmb ");
		sql.append("WHERE servico_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<PecaCMB> lista = new ArrayList<PecaCMB>();
		
		while (resultado.next()) {
			PecaCMB peca = new PecaCMB();
			peca.setId(resultado.getInt("id"));
			peca.setServico(servicoDAO.buscar(resultado.getInt("servico_id")));
			peca.setDescricao(resultado.getString("descricao"));
			peca.setPeso(resultado.getFloat("peso"));
			
			lista.add(peca);
		}
		
		return lista;
	}

	
	public void excluiTodasPecasDeUmServico(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM pecacmb");
		sql.append("WHERE servico_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, servico.getId());
		
		comando.executeUpdate();
	}
}
