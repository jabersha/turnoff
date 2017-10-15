package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.bean.Cidade;
import br.com.fiap.dao.CidadeDAO;

public class CidadeBO {
	
	public static List<Cidade> consultarPorCodigo(int codigo) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> x = dao.consultaPorCodigo(codigo);
		dao.fechar();
		return x;
	}
	
	public static List<Cidade> consultarPorNomeEstado(String nome) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> x = dao.consultaPorNomeEstado(nome);
		dao.fechar();
		return x;
	}
	
	public static List<Cidade> consutlarPorSigla(String sigla) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> x = dao.consultaPorSigla(sigla);
		dao.fechar();
		return x;
	}
}
