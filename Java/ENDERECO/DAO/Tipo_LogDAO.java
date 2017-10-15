package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.bean.Tipo_Log;
import br.com.fiap.conexao.ConexaoFactory;

public class Tipo_LogDAO {
	private Connection con;
	
	public Tipo_LogDAO() throws Exception{
		con = new ConexaoFactory().Conectar();
	}
	
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}
	
	public String insert(Tipo_Log tl) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_TIPO_LOG(cd_tipo_log, ds_tipo_log)"
		+"VALUES (?,?)");
		ps.setInt(1, tl.getCd_tipo_log());
		ps.setString(2, tl.getDs_tipo_log());
		ps.executeUpdate();
		ps.close();
		
		return "Inserido com sucesso!";
	}
	
	public Tipo_Log consultar() throws Exception {
		Tipo_Log tl = new Tipo_Log();
		PreparedStatement ps = con.prepareStatement
				("SELECT * FROM T_VFC_TIPO_LOG");
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("CODIGO DO LOGRADOURO: " + rs.getInt("cd_tipo_log"));
					System.out.println("DESCRIÇÃO DO LOGRADOURO: " + rs.getString("ds_tipo_log"));
					System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = =");
				}
				ps.close();
				rs.close();
				return tl;
	}
	
	public List<Tipo_Log> consultarPorCodigo(int cod) throws Exception {
		List<Tipo_Log> list = new ArrayList<>();
		Tipo_Log tl = new Tipo_Log();
		
		PreparedStatement ps = con.prepareStatement
		("SELECT cd_tipo_log, ds_tipo_log" + 
		" FROM T_VFC_TIPO_LOG" +   
		" WHERE cd_tipo_log = ?"); 
		ps.setInt(1, cod);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			tl = new Tipo_Log();
			tl.setCd_tipo_log(resultado.getInt("cd_tipo_log"));
			tl.setDs_tipo_log(resultado.getString("ds_tipo_log"));
			list.add(tl);
		}
		resultado.close();
		ps.close();
		return list;
	}
	
	public Tipo_Log consultaPorDescricao(String des) throws Exception {
		Tipo_Log tl = new Tipo_Log();
		PreparedStatement ps = con.prepareStatement
		("SELECT * FROM T_VFC_TIPO_LOG WHERE cd_tipo_log = ?");
		ps.setString(1,des);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("CODIGO DO LOGRADOURO: " + rs.getInt("cd_tipo_log"));
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = =");
		}
		ps.close();
		rs.close();
		return tl;
	}
	
	public String atualizar(Tipo_Log tl) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("UPDATE T_VFC_TIPO_LOG SET ds_tipo_log = ?"
		+ "WHERE cd_tipo_log = ?");
		ps.setString(1, tl.getDs_tipo_log());
		ps.setInt(2, tl.getCd_tipo_log());
		
		ps.execute();
		
		int x = ps.executeUpdate();
		ps.close();
		return x + " Linhas alteradas!";
	}
	
	public String excluir(Tipo_Log tl) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("DELETE FROM T_VFC_TIPO_LOG WHERE cd_tipo_log = ?");
		ps.setInt(1, tl.getCd_tipo_log());
		
		ps.execute();
		ps.close();
		
		return "Tipo Logradouro foi deletado!";
	}
	
}