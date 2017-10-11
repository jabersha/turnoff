public class Patio {
	private int cd_patio;
	private int qt_capacidade_max;
	private int qt_capacidade_atual;
	
	public Patio() {
		
	}
	
	public Patio(int cd_patio, int qt_capacidade_max, int qt_capacidade_atual) {
		this.cd_patio = cd_patio;
		this.qt_capacidade_max = qt_capacidade_max;
		this.qt_capacidade_atual = qt_capacidade_atual;
	}
	public int getCd_patio() {
		return cd_patio;
	}
	public void setCd_patio(int cd_patio) {
		this.cd_patio = cd_patio;
	}
	public int getQt_capacidade_max() {
		return qt_capacidade_max;
	}
	public void setQt_capacidade_max(int qt_capacidade_max) {
		this.qt_capacidade_max = qt_capacidade_max;
	}
	public int getQt_capacidade_atual() {
		return qt_capacidade_atual;
	}
	public void setQt_capacidade_atual(int qt_capacidade_atual) {
		this.qt_capacidade_atual = qt_capacidade_atual;
	}
}
