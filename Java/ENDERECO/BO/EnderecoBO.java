package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.bean.Endereco;
import br.com.fiap.bean.Estado;
import br.com.fiap.dao.EnderecoDAO;

public class EnderecoBO {
	
	public static List<Endereco> consultarPorCep(String cep) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		List<Endereco> e = dao.consultaPorCep(cep);
		dao.fechar();
		return e;
	}
}
