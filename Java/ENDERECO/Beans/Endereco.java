package br.com.fiap.bean;

import java.util.List;

public class Endereco {
	private String nr_cep;
	private int cd_bairro;
	private int cd_tipo_log;
	private String ds_endereco;
	private List<Tipo_Log> listaTipoLog;
	private List<Bairro> listaBairro;
	
	
	
	public Endereco() {
		
	}
	public Endereco(String nr_cep, int cd_bairro, int cd_tipo_log, String ds_endereco, List<Tipo_Log> listaTipoLog,
			List<Bairro> listaBairro) {
		super();
		this.nr_cep = nr_cep;
		this.cd_bairro = cd_bairro;
		this.cd_tipo_log = cd_tipo_log;
		this.ds_endereco = ds_endereco;
		this.listaTipoLog = listaTipoLog;
		this.listaBairro = listaBairro;
	}


	public List<Tipo_Log> getListaTipoLog() {
		return listaTipoLog;
	}


	public void setListaTipoLog(List<Tipo_Log> listaTipoLog) {
		this.listaTipoLog = listaTipoLog;
	}


	public List<Bairro> getListaBairro() {
		return listaBairro;
	}


	public void setListaBairro(List<Bairro> listaBairro) {
		this.listaBairro = listaBairro;
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
