package br.com.fiap.bo;

import br.com.fiap.bean.Cidade;
import br.com.fiap.dao.CidadeDAO;

public class CidadeBO {
	public static String inserir(Cidade c) throws Exception {
		CidadeDAO dao = new CidadeDAO();
		String msg = dao.insert(c);
		dao.fechar();
		return msg;
	}
}
