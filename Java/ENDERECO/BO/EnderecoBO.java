package br.com.fiap.bo;

import br.com.fiap.bean.Endereco;
import br.com.fiap.bean.Estado;
import br.com.fiap.dao.EnderecoDAO;

public class EnderecoBO {
	public static String inserir(Endereco e) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		String msg = dao.insert(e);
		dao.fechar();
		return msg;
	}
	
	public static String atualizar(Endereco e) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		String msg = dao.atualizar(e);
		dao.fechar();
		return msg;
	}
	
	public static String deletar(int codigo) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		String msg = dao.deletar(codigo);
		dao.fechar();
		return msg;
	}
	
	public static Endereco consultarPorCep(String cep) throws Exception {
		EnderecoDAO dao = new EnderecoDAO();
		Endereco e = dao.consultaPorCep(cep);
		dao.fechar();
		return e;
	}
}
