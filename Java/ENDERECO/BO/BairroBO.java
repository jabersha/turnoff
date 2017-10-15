package br.com.fiap.bo;

import br.com.fiap.bean.Bairro;
import br.com.fiap.dao.BairroDAO;

public class BairroBO {
	public static String inserir() throws Exception{
		BairroDAO dao = new BairroDAO();
		String msg = null;
		dao.fechar();
		return msg;
	}
	
	public static String deletar(Bairro b) throws Exception {
		BairroDAO dao = new BairroDAO();
		String x = dao.deletar(b);
		dao.fechar();
		return x;
	}
	
	public static String atualizar(Bairro b) throws Exception {
		BairroDAO dao = new BairroDAO();
		String x = dao.deletar(b);
		dao.fechar();
		return x;
	}
	
	public static Bairro consultar(int numero) throws Exception {
		BairroDAO dao = new BairroDAO();
		Bairro x = dao.consultar(numero);
		dao.fechar();
		return x;
	}
}