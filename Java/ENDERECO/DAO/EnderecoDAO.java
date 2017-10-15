package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Endereco;
import br.com.fiap.bean.Estado;
import br.com.fiap.conexao.ConexaoFactory;

public class EnderecoDAO {
	private Connection con;
	
	public EnderecoDAO() throws Exception {
		con = new ConexaoFactory().Conectar();
	}
	
	public String fechar() throws Exception {
		con.close();
		return "Fechado";
	}


	public String insert(Endereco e) throws Exception {
		PreparedStatement ps = con.prepareStatement
		("INSERT INTO T_VFC_ENDERECO(nr_cep, cd_bairro, cd_tipo_log, ds_endereco)"
				+"VALUES (?,?,?,?)");
		ps.setString(1, e.getNr_cep());
		ps.setInt(2, e.getCd_bairro());
		ps.setInt(3, e.getCd_tipo_log());
		ps.setString(4, e.getDs_endereco());
		ps.execute();

		ps.close();
		return "Inserido com sucesso!";
	}
	
	public String atualizar(Endereco e) throws Exception {
		PreparedStatement ps = con.prepareStatement
				("UPDATE T_VFC_ENDERECO SET cd_bairro = ?, cd_tipo_log = ?, ds_endereco = ?)" +
				"WHERE nr_cep = ?");
		ps.setInt(1, e.getCd_bairro());
		ps.setInt(2, e.getCd_tipo_log());
		ps.setString(3, e.getDs_endereco());
		ps.setString(4, e.getNr_cep());
		ps.executeUpdate();
		ps.close();
		return "Registro atualizado com sucesso!";
	}
	
	public String deletar(int codigo) throws Exception {
		Endereco e = new Endereco();
		PreparedStatement ps = con.prepareStatement
				("DELETE FROM T_VFC_ENDERECO WHERE nr_cep = ?");
		ps.setString(1, e.getNr_cep());
		ps.executeUpdate();
		ps.close();
		return "Registro deletado com sucesso!";
	}
	
	public Endereco consultaPorCep(String cep) throws Exception {
		Endereco e = new Endereco();
		PreparedStatement ps = con.prepareStatement
		("SELECT * FROM T_VFC_ENDERECO WHERE = ?");
		ps.setString(1, e.getNr_cep());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			System.out.println("DESCRICAO DO ENDERECO: " + e.getDs_endereco());
			System.out.println("CODIGO DO BAIRRO DO ENDERECO: " + e.getCd_bairro());
			System.out.println("CODIGO DO TIPO DO LOGRADOURO DO ENDERECO: " + e.getCd_tipo_log());
			
		}
		ps.execute();
		ps.close();
		return e;
	}
}
