package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.PecaPessoal;
import domain.Servico;
import factory.Conexao;

public class PecaPessoalDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ServicoDAO servicoDAO = new ServicoDAO();
	
	public void salvar(PecaPessoal pecaPessoal, Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO pecapessoal ");
		sql.append("(servico_id, descricao, delicada, quantidade ) ");
		sql.append("VALUES (?, ?, ?, ?) ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, servico.getId());
		comando.setString(2, pecaPessoal.getDescricao());
		comando.setBoolean(3, pecaPessoal.isDelicada());
		comando.setInt(4, pecaPessoal.getQuantidade());
		
		comando.executeUpdate();
	}
	
	public void excluir(PecaPessoal pecaPessoal) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM pecapessoal");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, pecaPessoal.getId());
		
		comando.executeUpdate();
	}
	
	public void atualizar(PecaPessoal pecaPessoal) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE pecapessoal ");
		sql.append("SET descricao = ? ");
		sql.append("SET delicada = ? ");
		sql.append("SET quantidade = ? ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, pecaPessoal.getDescricao());
		comando.setBoolean(2, pecaPessoal.isDelicada());
		comando.setInt(3, pecaPessoal.getQuantidade());
		comando.setInt(4, pecaPessoal.getId());
		
		comando.executeUpdate();
	}
	
	public PecaPessoal buscar(int id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, servico_id, descricao, delicada, quantidade");
		sql.append("FROM pecapessoal ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, id);
		
		ResultSet resultado = comando.executeQuery();
		
		PecaPessoal retorno = null;
		
		if (resultado.next()) {
			retorno = new PecaPessoal();
			retorno.setId(resultado.getInt("id"));
			retorno.setServico(servicoDAO.buscar(resultado.getInt("servico_id")));
			retorno.setDescricao(resultado.getString("descricao"));
			retorno.setDelicada(resultado.getBoolean("delicada"));
			retorno.setQuantidade(resultado.getInt("quantidade"));
		}
		
		return retorno;
	}
	
	public ArrayList<PecaPessoal> buscarTodasPecasDeUmServico(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, servico_id, descricao, peso ");
		sql.append("FROM pecapessoal ");
		sql.append("WHERE servico_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<PecaPessoal> lista = new ArrayList<PecaPessoal>();
		
		while (resultado.next()) {
			PecaPessoal peca = new PecaPessoal();
			peca.setId(resultado.getInt("id"));
			peca.setServico(servicoDAO.buscar(resultado.getInt("servico_id")));
			peca.setDescricao(resultado.getString("descricao"));
			peca.setDelicada(resultado.getBoolean("delicada"));
			peca.setQuantidade(resultado.getInt("quantidade"));
			
			lista.add(peca);
		}
		
		return lista;
	}
	
	public void excluiTodasPecasDeUmServico(Servico servico) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM pecapessoal");
		sql.append("WHERE servico_id = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, servico.getId());
		
		comando.executeUpdate();
	}
	
}
