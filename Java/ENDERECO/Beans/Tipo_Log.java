package br.com.fiap.bean;

public class Tipo_Log {
	private int cd_tipo_log;
	private String ds_tipo_log;

	public Tipo_Log() {

	}

	public Tipo_Log(int cd_tipo_log, String ds_tipo_log) {
		super();
		this.cd_tipo_log = cd_tipo_log;
		this.ds_tipo_log = ds_tipo_log;
	}

	public int getCd_tipo_log() {
		return cd_tipo_log;
	}

	public void setCd_tipo_log(int cd_tipo_log) {
		this.cd_tipo_log = cd_tipo_log;
	}

	public String getDs_tipo_log() {
		return ds_tipo_log;
	}

	public void setDs_tipo_log(String ds_tipo_log) {
		this.ds_tipo_log = ds_tipo_log;
	}
}
