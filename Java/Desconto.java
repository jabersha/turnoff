/*
  Esta é uma classe que só tem um método recebe a quantidade total de
  produtos e devolve um valor decimal para fazer reajuste no valor do frete.
*/
int qtTotal = 0; //Esse método vai recebendo a soma de quantidade total de produtos
public static double desc (int qtTotal) {
		if (qtTotal >= 10) {  //  Se a quantidade total de produtos for igual ou maior que 10
			return 0.10;        //  Retorna 10% de desconto/reajuste
		}
		else if (qtTotal >= 20) {
			return 0.20;
		}
		else if (qtTotal >= 35) {
			return 0.40;
		}
		return 0;
	}
