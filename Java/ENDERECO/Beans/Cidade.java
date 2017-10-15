package br.com.fiap.bean;

import java.util.List;

public class Cidade {
	private int cd_cidade;
	private String nm_cidade;
	private int cd_estado;
	private List<Estado> listaEstado;
	
	public Cidade() {
		
	}
	
	
	public Cidade(int cd_cidade, int cd_estado, String nm_cidade, List<Estado> listaEstado) {
		super();
		this.cd_cidade = cd_cidade;
		this.nm_cidade = nm_cidade;
		this.listaEstado = listaEstado;
	}
	
	public int getCd_estado() {
		return cd_estado;
	}


	public void setCd_estado(int cd_estado) {
		this.cd_estado = cd_estado;
	}

	


	public List<Estado> getListaEstado() {
		return listaEstado;
	}



	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}



	public int getCd_cidade() {
		return cd_cidade;
	}

	public void setCd_cidade(int cd_cidade) {
		this.cd_cidade = cd_cidade;
	}

	public String getNm_cidade() {
		return nm_cidade;
	}

	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}

}
