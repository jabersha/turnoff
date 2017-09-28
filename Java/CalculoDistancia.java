
public class CalculoDistancia {
	public static void main(String[] args) {
		
		//VARIAVEIS
		double coord[]= new double[]
	{-15.794, -47.882, -16.679, -49.255};
				
	double form =
			Math.sqrt(Math.pow(coord[0] - coord[2], 2) + Math.pow(coord[1] - coord[3], 2));
			//	Math.sqrt() retorna a raiz quadrada entre os parenteses
			//Math.pow() eleva o numero decimal para seu inteiro mais proximo. Ex, 2.77 vira 3
	
	
	System.out.println(form);
	}
}