package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Estado;
import br.com.fiap.bean.Tipo_Log;
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
	
	//CONSULTA POR CODIGO E RETORNA TODAS AS COLUNAS
		public List<Estado> consultaPorCodigo(int cod) throws Exception{
			List<Estado> list = new ArrayList<>();
			Estado e = new Estado();
			
			PreparedStatement ps = con.prepareStatement
			("SELECT cd_estado, nm_estado, ds_sigla" + 
			" FROM T_VFC_ESTADO" +   
			" WHERE cd_estado = ?"); 
			ps.setInt(1, cod);
			
			ResultSet resultado = ps.executeQuery();
			while(resultado.next()){
				e = new Estado();
				e.setCd_estado(resultado.getInt("cd_estado"));
				e.setNm_estado(resultado.getString("nm_estado"));
				e.setDs_sigla(resultado.getString("ds_sigla"));
				list.add(e);
			}
			resultado.close();
			ps.close();
			return list;
		}
		public List<Estado> consultaPorSigla(String sigla) throws Exception{
			List<Estado> list = new ArrayList<>();
			Estado e = new Estado();
			
			PreparedStatement ps = con.prepareStatement
			("SELECT cd_estado, nm_estado, ds_sigla" + 
			" FROM T_VFC_ESTADO" +   
			" WHERE ds_sigla = ?"); 
			ps.setString(1, sigla);
			
			ResultSet resultado = ps.executeQuery();
			while(resultado.next()){
				e = new Estado();
				e.setCd_estado(resultado.getInt("cd_estado"));
				e.setNm_estado(resultado.getString("nm_estado"));
				e.setDs_sigla(resultado.getString("ds_sigla"));
				list.add(e);
			}
			resultado.close();
			ps.close();
			return list;
		}
	
}