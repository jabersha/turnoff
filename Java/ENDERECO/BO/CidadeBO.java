package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.dao.CidadeDAO;

public class CidadeBO {
	public static String inserir(Cidade c) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		String msg = dao.insertEstadoCidade(c);
		dao.fechar();
		return msg;
	}
	public static List<Cidade> consultaPorSigla(String sigla) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> x = dao.consultaPorSigla(sigla);
		dao.fechar();
		return x;
	}
	
	public static List<Cidade> consultaPorEstado(String sigla) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> x = dao.consultaPorNomeEstado(sigla);
		dao.fechar();
		return x;
	}
}
