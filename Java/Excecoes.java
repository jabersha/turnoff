public class Excecao extends Exception{
	
	public Excecao(Exception e){
		
		if (e instanceof ArrayIndexOutOfBoundsException){
			System.out.println("Excedeu limite");	
		}
		else if (e instanceof NumberFormatException){
			System.out.println("Número inválido");
			
		}
		else if (e instanceof ArithmeticException) {
			System.out.println("Não é possivel dividir por 0");
			
		}
		else if (e instanceof IllegalArgumentException) {
			System.out.println("Paramatro fora do padrão");
			
		}
		else if (e instanceof NullPointerException) {
			System.out.println("O atributo está nulo");
			
		}
		else{
			System.out.println("Erro desconhecido");
		}
	}
}
