package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.bean.Estado;
import br.com.fiap.dao.EstadoDAO;

public class EstadoBO {
	public static String inserir(Estado e) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		String msg = dao.insert(e);
		dao.fechar();
		return msg;
	}

	public static List<Estado> consultarPorCodigo(int c) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> e = dao.consultaPorCodigo(c);
		dao.fechar();
		return e;
	}

	public static List<Estado> consultaPorSigla(String s) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> e = dao.consultaPorSigla(s);
		dao.fechar();
		return e;
	}

}
