package br.com.fiap.bo;

import java.util.List;

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
	
	public static List<Bairro> consultarPorCodigo(int numero) throws Exception {
		BairroDAO dao = new BairroDAO();
		List<Bairro> x = dao.consultarPorCodigo(numero);
		dao.fechar();
		return x;
	}
	
	public static List<Bairro> consultarPorCodigoCidade(int numero) throws Exception {
		BairroDAO dao = new BairroDAO();
		List<Bairro> x = dao.consultaPorCodigoCidade(numero);
		dao.fechar();
		return x;
	}
}