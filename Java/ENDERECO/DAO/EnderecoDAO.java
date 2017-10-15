package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bean.Bairro;
import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Endereco;
import br.com.fiap.bean.Estado;
import br.com.fiap.bean.Tipo_Log;
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

	public List<Endereco> consultaPorCep(String cep) throws Exception {
		List<Endereco> list = new ArrayList<>();
		Endereco e = new Endereco();
		PreparedStatement ps = con.prepareStatement
		("SELECT E.NR_CEP , E.CD_BAIRRO , E.CD_TIPO_LOG, E.DS_ENDERECO, TP.DS_TIPO_LOG, B.NM_BAIRRO" +
		"FROM T_VFC_ENDERECO E INNER JOIN T_VFC_TIPO_LOG TP ON (E.CD_TIPO_LOG = TP.CD_TIPO_LOG)" + 
		"INNER JOIN T_VFC_BAIRRO ON (E.CD_BAIRRO = B.CD_BAIRRO)" + 
		"WHERE NR_CEP = ?;");
		ps.setString(1, e.getNr_cep());
		ResultSet rs = ps.executeQuery();
		
		ResultSet resultado = ps.executeQuery();
		while(resultado.next()){
			
			e = new Endereco();
			e.setNr_cep(resultado.getString("nr_cep"));
			e.setCd_bairro(resultado.getInt("cd_bairro"));
			e.setCd_tipo_log(resultado.getInt("cd_tipo_log"));
			e.setDs_endereco(resultado.getString("ds_endereco"));
			
			BairroDAO bDao = new BairroDAO();
			List<Bairro> listaBairro = 
			bDao.consultarPorCodigo(resultado.getInt("cd_bairro"));
			bDao.fechar();
			e.setListaBairro(listaBairro);
			
			Tipo_LogDAO tDao = new Tipo_LogDAO();
			List<Tipo_Log> listaTipoLog =
			tDao.consultarPorCodigo(resultado.getInt("cd_tipo_log"));
			tDao.fechar();
			e.setListaTipoLog(listaTipoLog);
			
			
			list.add(e);
		}
		ps.execute();
		ps.close();
		return list;
	}
	
}
