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
	
//	public int RecuperarID()throws Exception{
//
//		PreparedStatement estrutura  = con.prepareStatement("SELECT SQ_ESTADO.NEXTVAL \"ID\" FROM SYS.DUAL");
//
//		ResultSet rs = estrutura.executeQuery();
//
//		int codigo = 0;
//
//		if(rs.next()){
//			codigo = rs.getInt("ID");
//		}
//
//		rs.close();
//		estrutura.close();
//		return codigo;
//
//	}
	
	public String insert(Cidade c) throws Exception {
		
		Estado e = new Estado();
		
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_ESTADO(cd_estado, nm_estado, ds_sigla)"
				+"VALUES (?,?,?)");
		ps.setInt(1, e.getCd_estado());
		ps.setString(2, e.getNm_estado());
		ps.setString(3, e.getDs_sigla());
		ps = con.prepareStatement
		("INSERT INTO T_VFC_CIDADE"
		+"VALUES (?,?,?)");
		ps.setInt(1, c.getCd_cidade());
		ps.setString(2, c.getNm_cidade());
		ps.setInt(3, e.getCd_estado());
		ps.execute();
//		for (Estado e : c.getListaEstado()) {
//			new EstadoDAO().insert(e, c.getCd_cidade());
//		}
		ps.close();
		return "Inserido com sucesso!";
	}
//	public String insert(Cidade c) throws Exception {
//		
//		Estado e = new Estado();
//		PreparedStatement ps = con.prepareStatement
//		("INSERT INTO T_VFC_CIDADE (cd_cidade, nm_cidade, cd_estado)"
//		+"VALUES (?,?,?)");
//		ps.setInt(1, c.getCd_cidade());
//		ps.setString(2, c.getNm_cidade());
//		ps.setInt(3, c.getCd_estado());
//		ps.execute();
//		ps.close();
//		return "Inserido com sucesso!";
//	}
	
}
