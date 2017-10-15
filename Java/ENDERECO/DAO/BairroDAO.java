package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.bean.Bairro;
import br.com.fiap.bean.Cidade;
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
	
//	public String insert(Bairro b) throws Exception {
//		PreparedStatement ps = con.prepareStatement
//		("INSERT INTO T_VFC_BAIRRO(cd_bairro, nm_bairro, cd_cidade)"
//		+"VALUES (?,?,?)");
//		ps.setInt(1, b.getCd_bairro());
//		ps.setString(2, b.getNm_bairro());
//		for (Cidade c : b.getListaCidade()) {
//			new CidadeDAO().insert(c);
//		}
//		ps.executeUpdate();
//		ps.close();
//		
//		return "Inserido com sucesso!";
//	}
	
	public String insert(Bairro b) throws Exception{
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_BAIRRO (cd_bairro, nm_bairro, cd_cidade" +
		"VALUES (?,?,?)");
		ps.setInt(1, b.getCd_bairro());
		ps.setString(2, b.getNm_bairro());
		ps.setInt(3,b.getCd_cidade());
		ps.execute();
		ps.close();
		
		return "Inserido com sucesso!";
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
	
	public Bairro consultar(int numero) throws Exception {
		Bairro b = new Bairro();
		PreparedStatement estrutura = null;
		estrutura = con.prepareStatement
				("SELECT * FROM TB_DDD_CLIENTE WHERE NR_CLIENTE=?");
		estrutura.setInt(1, numero);
		ResultSet resultado = estrutura.executeQuery();
		if(resultado.next()){
			b.setCd_bairro(resultado.getInt("cd_bairro"));
			b.setNm_bairro(resultado.getString("nm_bairro"));
			b.setCd_cidade(resultado.getInt("cd_cidade"));
		}
		resultado.close();
		estrutura.close();
		return b;
	}
}