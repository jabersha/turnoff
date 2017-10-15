package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Estado;
import br.com.fiap.conexao.ConexaoFactory;

public class EstadoDAO {
	private Connection con;
	
	public EstadoDAO() throws Exception {
		con = new ConexaoFactory().Conectar();
	}
	
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}
	
	public String insert(Estado e) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_ESTADO(cd_estado, nm_estado, ds_sigla)"
		+"VALUES (?,?,?)");
		ps.setInt(1, e.getCd_estado());
//		ps.setInt(1, cd_estado);
		ps.setString(2, e.getNm_estado());
		ps.setString(3, e.getDs_sigla());
		ps.executeUpdate();
		ps.close();
		
		return "Inserido com sucesso!";
	}
	
	//CONSULTA POR CODIGO E RETORNA TODAS AS COLUNAS
	public Estado consultaPorCodigo(int cod) throws Exception{
		Estado est = new Estado();
		PreparedStatement ps = con.prepareStatement
		("SELECT * FROM T_VFC_ESTADO WHERE cd_estado = ?");
		ps.setInt(1,cod);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			System.out.println("NOME DO ESTADO: " + rs.getString("nm_estado"));
			System.out.println("SIGLA DO ESTADO: " + rs.getString("ds_sigla"));
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = =");
		}
//		ps.execute();
		ps.close();
		rs.close();
		return est;
	}
	// = = = CONSULTA POR SIGLA 
//	public Estado consultaPorSigla(String sig) throws Exception {
//		Estado est = new Estado();
//		PreparedStatement ps = con.prepareStatement
//			("SELECT * FROM T_VFC_ESTADO WHERE ds_sigla= ?");
//		ps.setString(1,sig);
//		ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				System.out.println("NOME DO ESTADO: " + rs.getString("nm_estado"));
//				System.out.println("SIGLA DO ESTADO: " + rs.getString("ds_sigla"));
//				System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = =");
//			}
//			
//			ps.execute();
//			ps.close();
//			return est;
//	}
	
	public List<Estado> consultaPorSigla(String sigla)throws Exception{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM T_VFC_ESTADO WHERE DS_SIGLA = ?");
		stmt.setString(1, sigla);
		ResultSet rs = stmt.executeQuery();
		List<Estado> list = new ArrayList<>();
		while(rs.next()){
			list.add(new Estado
					(rs.getInt("CD_ESTADO"), 
					rs.getString("NM_ESTADO"), 
					rs.getString("DS_SIGLA")));
		}
		rs.close();
		stmt.close();
		return list;
	}
			
	public String atualizar(Estado e) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("UPDATE T_VFC_ESTADO SET nm_estado = ?, ds_sigla = ?"
		+ "WHERE cd_estado = ?");
		ps.setString(1, e.getNm_estado());
		ps.setString(2, e.getDs_sigla());
		ps.setInt(3, e.getCd_estado());
		
		ps.execute();
		
		int x = ps.executeUpdate();
		ps.close();
		return x + " Linhas alteradas!";
	}
	
	public String excluir(Estado e) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("DELETE FROM T_VFC_ESTADO WHERE cd_estado = ?");
		ps.setInt(1, e.getCd_estado());
		
		ps.execute();
		ps.close();
		
		return "Estado deletado!";
	}
}