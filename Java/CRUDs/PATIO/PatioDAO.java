package br.com.tsystem.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.tsystem.fiap.beans.Patio;

public class PatioDAO {
	private Connection con;

	public PatioDAO() throws Exception {
		con = new ConexaoFactory().conectar();
	}

	public String fechar() throws Exception {
		con.close();
		return "Fechou!";
	}

	public String inserir(Patio pat) throws Exception {
		PreparedStatement estrutura = con.prepareStatement(
				"INSERT INTO T_VFC_PATIO (CD_PATIO, QT_CAPACIDADE_MAX, QT_CAPACIDADE_ATUAL)" + "VALUES (?, ?, ?)");
		estrutura.setInt(1, pat.getCd_patio());
		estrutura.setInt(2, pat.getQt_capacidade_max());
		estrutura.setInt(3, pat.getQt_capacidade_atual());
		estrutura.execute();
		estrutura.close();
		return "Inserido com sucesso!";
	}

	public int excluir(int codigo) throws Exception {
		PreparedStatement estrutura = con.prepareStatement("DELETE FROM T_VFC_PATIO WHERE CD_PATIO = ?");
		estrutura.setInt(1, codigo);
		int x = estrutura.executeUpdate();
		estrutura.close();
		return x;
	}

	public List<Patio> consulta(int codigo) throws Exception {
		List<Patio> lista = new ArrayList<>();
		Patio p = new Patio();

		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement("SELECT * FROM TB_DDD_CLIENTE WHERE CD_PATIO = ?");
		estrutura.setInt(1, codigo);
		ResultSet resultado = estrutura.executeQuery();
		while (resultado.next()) {
			p = new Patio();
			p.setQt_capacidade_atual(resultado.getInt("QT_CAPACIDADE_ATUAL"));
			p.setQt_capacidade_max(resultado.getInt("QT_CAPACIDADE_MAXIMA"));

			lista.add(p);
		}
		resultado.close();
		estrutura.close();
		return lista;
	}
  
	public int alterar(int codigo, int atual, int max) throws Exception {
		PreparedStatement estrutura = con.prepareStatement(
				"UPDATE T_VFC_PATIO SET QT_CAPACIDADE_ATUAL = ?, QT_CAPACIDADE_MAXIMA = ? +" + "WHERE CD_CODIGO = ?");
		estrutura.setInt(1, atual);
		estrutura.setInt(2, max);
		estrutura.setInt(3, codigo);
		int x = estrutura.executeUpdate();
		estrutura.close();
		return x;
	}

}
