package br.com.fiap.bean;

public class Estado {
	private int cd_estado;
	private String nm_estado;
	private String ds_sigla;

	public Estado() {

	}

	public Estado(int cd_estado, String nm_estado, String ds_sigla) {
		super();
		this.cd_estado = cd_estado;
		this.nm_estado = nm_estado;
		this.ds_sigla = ds_sigla;
	}

	public int getCd_estado() {
		return cd_estado;
	}

	public void setCd_estado(int cd_estado) {
		this.cd_estado = cd_estado;
	}

	public String getNm_estado() {
		return nm_estado;
	}

	public void setNm_estado(String nm_estado) {
		this.nm_estado = nm_estado;
	}

	public String getDs_sigla() {
		return ds_sigla;
	}

	public void setDs_sigla(String ds_sigla) {
		this.ds_sigla = ds_sigla;
	}
}
