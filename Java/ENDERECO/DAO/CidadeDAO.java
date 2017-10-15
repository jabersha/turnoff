package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.conexao.ConexaoFactory;

public class CidadeDAO {
	private Connection con;
	
	public CidadeDAO() throws Exception {
		con = new ConexaoFactory().Conectar();
	}
	
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}

	public String insert(Cidade c) throws Exception {
		
		Estado e = new Estado();
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_CIDADE (cd_cidade, nm_cidade, cd_estado)"
		+"VALUES (?,?,?)");
		ps.setInt(1, c.getCd_cidade());
		ps.setString(2, c.getNm_cidade());
		ps.setInt(3, c.getCd_estado());
		ps.execute();
		ps.close();
		return "Inserido com sucesso!";
	}
}
