package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	//Faz a consulta na tabela cidade e depois faz subconsultap para buscar a sigla
	public List<Cidade> consultaPorSigla(String sigla) throws Exception {
		
		List<Cidade> list = new ArrayList<>();
		Cidade cid = new Cidade();
		PreparedStatement ps = con.prepareStatement
		("SELECT C.cd_cidade, C.nm_cidade, E.cd_estado, E.nm_estado, E.ds_sigla" + 
		" FROM T_VFC_CIDADE C INNER JOIN T_VFC_ESTADO E ON (C.CD_ESTADO = E.CD_ESTADO)" + //Subquery em estado pela fk de cidade   
		" WHERE E.ds_sigla = ? " + //Valor para a sigla
		" ORDER BY CD_CIDADE"); //Ordena pelo código da cidade
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