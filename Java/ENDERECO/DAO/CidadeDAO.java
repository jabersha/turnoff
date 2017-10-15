package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.conexao.ConexaoFactory;
/**
 * Esta classe realiza as consultas baseadas na sigla, nome e codigo do estado
 * @author Vitor
 * @since 14-10-2017
 */
public class CidadeDAO {
	private Connection con;
	
	/**
	 * Este metodo realiza a conexao com SQL via classe ConexaoFactory pelo método Conectar
	 * @throws Exception
	 */
	public CidadeDAO() throws Exception {
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
	 * Este metodo recebe como parametro a sigla do estado, para devolver os valores exigidos  
	 * @param sigla - parametro que devolve os valores que contem a sigla inserida pelo usuario
	 * @return lista para o programa
	 * @throws Exception
	 */
	public List<Cidade> consultaPorSigla(String sigla) throws Exception {
		
		List<Cidade> list = new ArrayList<>();
		Cidade cid = new Cidade();
		PreparedStatement ps = con.prepareStatement
		("SELECT C.cd_cidade, C.nm_cidade, E.cd_estado, E.nm_estado, E.ds_sigla" + 
		" FROM T_VFC_CIDADE C INNER JOIN T_VFC_ESTADO E ON (C.CD_ESTADO = E.CD_ESTADO)"+   
		" WHERE E.ds_sigla = ? " +
		" ORDER BY CD_CIDADE");
		ps.setString(1, sigla);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			cid = new Cidade();
			cid.setCd_cidade(resultado.getInt("cd_cidade"));
			cid.setNm_cidade(resultado.getString("nm_cidade"));
			EstadoDAO eDao = new EstadoDAO();
			List<Estado> listaEstado = 
			eDao.consultaPorSigla(resultado.getString("ds_sigla"));
			eDao.fechar();
			cid.setListaEstado(listaEstado);
			
			list.add(cid);
		}
		resultado.close();
		ps.close();
		return list;
	}
	/**
	 * Este metodo recebe um nome de Estado e devolve os valores exigidos
	 * @param nome - o nome do estado
	 * @return retorna a lista para o programa
	 * @throws Exception
	 */
	public List<Cidade> consultaPorNomeEstado(String nome) throws Exception{

		List<Cidade> list = new ArrayList<>();
		Cidade cid = new Cidade();
		
		PreparedStatement ps = con.prepareStatement
		("SELECT C.cd_cidade, C.nm_cidade, E.cd_estado, E.nm_estado, E.ds_sigla" + 
		" FROM T_VFC_CIDADE C INNER JOIN T_VFC_ESTADO E ON (C.CD_ESTADO = E.CD_ESTADO)" + //Subquery em estado pela fk de cidade   
		" WHERE E.nm_estado = ? " + //Retorna os valores aonde o nome de estado for inserido pelo usuario 
		" ORDER BY E.nm_estado"); //Ordena pelo nome do estado
		ps.setString(1, nome);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			cid = new Cidade();
			cid.setCd_cidade(resultado.getInt("cd_cidade"));
			cid.setNm_cidade(resultado.getString("nm_cidade"));
			EstadoDAO eDao = new EstadoDAO();
			List<Estado> listaEstado = 
			eDao.consultaPorSigla(resultado.getString("ds_sigla"));
			eDao.fechar();
			cid.setListaEstado(listaEstado);
			
			list.add(cid);
		}
		resultado.close();
		ps.close();
		return list;
	}
	
	/**
	 * O seguinte metodo realiza a consulta que busca pela tabela de acordo com a identificacao da tabela
	 * @param codigo - é achave primaria da tabela para a consulta
	 * @return retorna a lista para o programa
	 * @throws Exception
	 */
	public List<Cidade> consultaPorCodigo(int codigo) throws Exception {
		List<Cidade> list = new ArrayList<>();
		Cidade cid = new Cidade();
		
		PreparedStatement ps = con.prepareStatement
		("SELECT cd_cidade, nm_cidade, cd_estado" + 
		" FROM T_VFC_CIDADE" +   
		" WHERE cd_cidade = ?"); 
		ps.setInt(1, codigo);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			cid = new Cidade();
			cid.setCd_cidade(resultado.getInt("cd_cidade"));
			cid.setNm_cidade(resultado.getString("nm_cidade"));
			cid.setCd_estado(resultado.getInt("cd_estado"));
			list.add(cid);
		}
		resultado.close();
		ps.close();
		return list;
	}
}