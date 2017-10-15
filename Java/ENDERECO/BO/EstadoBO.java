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

	public static Estado consultarPorCodigo(int c) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		Estado e = dao.consultaPorCodigo(c);
		dao.fechar();
		return e;
	}

	public static List<Estado>consultaPorSigla(String s) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> lista= dao.consultaPorSigla(s);
		dao.fechar();
		return lista;
	}

	public static Estado excluir(int c) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		Estado e = excluir(c);
		dao.fechar();
		return e;
	}

	public static String atualizar(Estado e) throws Exception {
		EstadoDAO dao = new EstadoDAO();
		String x = dao.atualizar(e);
		dao.fechar();
		return x;
	}
}
