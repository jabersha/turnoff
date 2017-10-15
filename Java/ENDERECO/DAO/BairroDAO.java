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

public class BairroDAO {

private Connection con;
	
	public BairroDAO() throws Exception {
		con = new ConexaoFactory().Conectar();
	}
	
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}
	
	public String atualizar(Bairro b) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("UPDATE T_VFC_BAIRRO SET (nm_bairro = ?, cd_cidade = ?" +
		 "WHERE cd_bairro = ?");
		ps.setString(1, b.getNm_bairro());
		ps.setInt(2, b.getCd_cidade());
		ps.setInt(3, b.getCd_bairro());
		
		ps.executeUpdate();
		ps.close();
		return "Registro atualizado com sucesso!";
	}
	
	public String deletar(Bairro b) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("DELETE FROM T_VFC_BAIRRO WHERE cd_bairro = ?" );
		ps.setInt(1, b.getCd_bairro());
		ps.executeUpdate();
		ps.close();
		return "Deletado com sucesso!";
	}
	
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