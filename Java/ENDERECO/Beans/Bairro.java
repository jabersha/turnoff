package br.com.fiap.bean;

import java.util.List;

public class Bairro {
	private int cd_bairro;
	private String nm_bairro;
	private int cd_cidade;
	private List<Cidade> listaCidade;
	
	
	public Bairro() {
		
	}
	
	public Bairro(int cd_bairro, String nm_bairro, int cd_cidade, List<Cidade> listaCidade) {
		super();
		this.cd_bairro = cd_bairro;
		this.nm_bairro = nm_bairro;
		this.cd_cidade = cd_cidade;
		this.listaCidade = listaCidade;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	
	public int getCd_bairro() {
		return cd_bairro;
	}

	public void setCd_bairro(int cd_bairro) {
		this.cd_bairro = cd_bairro;
	}
	
	public String getNm_bairro() {
		return nm_bairro;
	}

	public void setNm_bairro(String nm_bairro) {
		this.nm_bairro = nm_bairro;
	}

	public int getCd_cidade() {
		return cd_cidade;
	}

	public void setCd_cidade(int cd_cidade) {
		this.cd_cidade = cd_cidade;
	}
	
}
