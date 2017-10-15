package br.com.fiap.testeCidade;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.bean.Cidade;
import br.com.fiap.bean.Estado;
import br.com.fiap.dao.CidadeDAO;

public class ConsultaPorNomeEstado {
	public static void main(String[] args) {
		CidadeDAO dao = null;
		try {
			dao = new CidadeDAO();
			// List<Cidade> lista =
			List<Cidade> lista = dao.consultaPorNomeEstado(JOptionPane.showInputDialog("NOME DA CIDADE"));

			// Puxa os atributos de Cidade primeiro
			for (Cidade c : lista) {
				System.out.println("CODIGO DA CIDADE:	" + c.getCd_cidade());
				System.out.println("NOME DA CIDADE:		" + c.getNm_cidade());
				// Puxa os atributos de Estado
				for (Estado e : c.getListaEstado()) {
					System.out.println("CODIGO DO ESTADO: 	" + e.getCd_estado());
					System.out.println("NOME DO ESTADO: 	" + e.getNm_estado());
					System.out.println("SIGLA DO ESTADO: 	" + e.getDs_sigla());
					System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = ");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dao.fechar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
