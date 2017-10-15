package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Estado;
import br.com.fiap.bean.Tipo_Log;
import br.com.fiap.conexao.ConexaoFactory;
/**
 * Esta classe
 * @author Vitor
 * @since 14-10-2017
 *
 */
public class EstadoDAO {
	private Connection con;
	/**
	 * Este metodo realiza a conexao com SQL via classe ConexaoFactory pelo método Conectar
	 * @throws Exception
	 */
	public EstadoDAO() throws Exception {
		con = new ConexaoFactory().Conectar();
	}
	/**
	 * O seguinte metodo fecha a conexao do banco de dados
	 * @return
	 * @throws Exception
	 */
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}
	/**
	 * Faz a consulta em Estado com base no codigo de identificacao da tabela
	 * @param cod - é o parametro para o codigo do estado
	 * @return retorna a lista para o programa
	 * @throws Exception
	 */
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
		/**
		 * Este metodo recebe como parametro a sigla do estado, para devolver os valores exigidos  
		 * @param sigla - parametro que devolve os valores que contem a sigla inserida pelo usuario
		 * @return lista para o programa
		 * @throws Exception
		 */
		
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