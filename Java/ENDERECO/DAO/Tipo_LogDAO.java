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
/**
 * 
 * @author Vitor
 * @since 15-10-2017
 * Esta classe é responsavel por realizar consultas referente á tabela Tipo_Log
 */
public class Tipo_LogDAO {
	private Connection con;
	
	/**
	 * Este metodo realiza a conexao com SQL via classe ConexaoFactory pelo método Conectar
	 */
	public Tipo_LogDAO() throws Exception{
		
		con = new ConexaoFactory().Conectar();
	}
	/**
	 * Este metodo fecha conexao
	 */
	public String fechar() throws Exception {
		
		con.close();
		return "Fechado";
	}

	/**
	 * Este método realiza a consulta por codigo na tabela do banco de dados pelo 
	 * através do codigo principal(chave priamria) da tabela
	 * @param cod é o numero que recebe de fora, geralmente pela classe main, para ser usado como valor para a consulta
	 */
	
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
}