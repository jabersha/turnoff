package br.com.fiap.bean;

public class Endereco {
	private String nr_cep;
	private int cd_bairro;
	private int cd_tipo_log;
	private String ds_endereco;

	public Endereco() {
		
	}
	
	public Endereco(String nr_cep, int cd_bairro, int cd_tipo_log, String ds_endereco) {
		super();
		this.nr_cep = nr_cep;
		this.cd_bairro = cd_bairro;
		this.cd_tipo_log = cd_tipo_log;
		this.ds_endereco = ds_endereco;
	}

	public String getNr_cep() {
		return nr_cep;
	}

	public void setNr_cep(String nr_cep) {
		this.nr_cep = nr_cep;
	}

	public int getCd_bairro() {
		return cd_bairro;
	}

	public void setCd_bairro(int cd_bairro) {
		this.cd_bairro = cd_bairro;
	}

	public int getCd_tipo_log() {
		return cd_tipo_log;
	}

	public void setCd_tipo_log(int cd_tipo_log) {
		this.cd_tipo_log = cd_tipo_log;
	}

	public String getDs_endereco() {
		return ds_endereco;
	}

	public void setDs_endereco(String ds_endereco) {
		this.ds_endereco = ds_endereco;
	}

}
