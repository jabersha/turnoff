package br.com.fiap.bo;

import br.com.fiap.bean.Estado;
import br.com.fiap.bean.Tipo_Log;
import br.com.fiap.dao.EstadoDAO;
import br.com.fiap.dao.Tipo_LogDAO;

public class Tipo_LogBO {
	public static String inserir(Tipo_Log tl) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		String msg = dao.insert(tl);
		dao.fechar();
		return msg;
	}
	
	public static Tipo_Log consultar() throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		Tipo_Log tl = dao.consultar();
		dao.fechar();
		return tl;
	}
	
	public static Tipo_Log consultarPorCodigo(int c) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		Tipo_Log tl = dao.consultarPorCodigo(c);
		dao.fechar();
		return tl;
	}
	
	public static Tipo_Log consultarPorDescricao(String d) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		Tipo_Log tl = dao.consultaPorDescricao(d);
		dao.fechar();
		return tl;
	}

	public static String atualizar(Tipo_Log tl) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		String x = dao.atualizar(tl);
		dao.fechar();
		
		return x;
	}
	public static Tipo_Log excluir(int c) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		Tipo_Log tl = excluir(c);
		dao.fechar();

		return tl;
	}
}
