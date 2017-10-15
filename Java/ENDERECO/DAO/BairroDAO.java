package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Bairro;
import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.conexao.ConexaoFactory;
/**
 * 
 * @author Vitor
 * @version final
 * @since 14-10-2017
 * Esta classe é responsavel por realizar consultas na tabela Bairro pela identificacao de bairro e de cidade
 */
public class BairroDAO {

private Connection con;
	
	/**
	 * Este metodo realiza a conexao com SQL via classe ConexaoFactory pelo método Conectar
	 * @throws Exception
	 */
	public BairroDAO() throws Exception {
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
	 * Este metodo como o nome diz, faz a consulta na tabela referente por codigo(chave primaria), ele recebe o codigo, e é devolvido os valores exigidos
	 * baseados no identificacao da tabela
	 * @param codigo - este parametro é recebido de fora, geralmente pela classe main, para ser usado como valor para a consulta. Que no caso é o codigo principal 
	 * @return retorna a lista para o programa
	 * @throws Exception
	 */
	public List<Bairro> consultarPorCodigo(int codigo) throws Exception {
		List<Bairro> list = new ArrayList<>();
		Bairro bai = new Bairro();
		
		PreparedStatement ps = con.prepareStatement
		("SELECT cd_bairro, cd_cidade, nm_bairro" + 
		" FROM T_VFC_BAIRRO" +    
		" WHERE cd_bairro = ? ");
		ps.setInt(1, codigo);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			bai = new Bairro();
			bai.setCd_bairro(resultado.getInt("cd_bairro"));
			bai.setCd_cidade(resultado.getInt("cd_cidade"));
			bai.setNm_bairro(resultado.getString("nm_bairro"));
			list.add(bai);
		}
		resultado.close();
		ps.close();
		return list;
	}
	/**
	 * Este metodo recebe a identificacao da chave da entidade pai, para retornar os valores de ambas as entidades, Cidade e Bairro
	 * @param codigo - é o parametro que recebe o codigo da tabela cidade de um programa main ou de fora.
	 * @return retorna a lista para o programa
	 * @throws Exception
	 */
	public List<Bairro> consultaPorCodigoCidade(int codigo) throws Exception {
		List<Bairro> list = new ArrayList<>();
		Bairro bai = new Bairro();
		Cidade cid = new Cidade();
		Estado est = new Estado();
		
		PreparedStatement ps = con.prepareStatement
		("SELEC B.cd_bairro , B.nm_bairro, B.cd_cidade, C.cd_cidade , C.cd_estado , C.nm_cidade" + 
		" FROM T_VFC_BAIRRO B INNER JOIN T_VFC_CIDADE C" +
		" ON (B.CD_CIDADE = C.CD_CIDADE)" +
		" WHERE P.cd_cidade = ?");
			ps.setInt(1, codigo);
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			bai = new Bairro();
			bai.setCd_bairro(resultado.getInt("cd_bairro"));
			bai.setCd_cidade(resultado.getInt("cd_cidade"));
			bai.setNm_bairro(resultado.getString("nm_bairro"));
			cid.setCd_cidade(resultado.getInt("cd_cidade"));
			cid.setCd_estado(resultado.getInt("cd_estado"));
			cid.setNm_cidade(resultado.getString("nm_cidade"));
			CidadeDAO cDao = new CidadeDAO();
			List<Cidade> listaCidade= 
			cDao.consultaPorCodigo(resultado.getInt("cd_cidade"));
			cDao.fechar();
			bai.setListaCidade(listaCidade);
			
			list.add(bai);
		}
		resultado.close();
		ps.close();
		return list;
	}
	
}