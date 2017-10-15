package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.bean.Tipo_Log;
import br.com.fiap.dao.Tipo_LogDAO;

public class Tipo_LogBO {
	public static List<Tipo_Log> consultarPorCodigo(int c) throws Exception {
		Tipo_LogDAO dao = new Tipo_LogDAO();
		List<Tipo_Log> tl = dao.consultarPorCodigo(c);
		dao.fechar();
		return tl;
	}
}
