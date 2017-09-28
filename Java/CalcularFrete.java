import java.util.Scanner;

/* -São percorridos 500km por dia 
 * -A cada 300 quilometro, um valor fixo de R$50.0*/

//	peso*((int)dist/kilometragem)*valor + valor_fixo
//	Mudar o valor fixo junto com a quilotragem percorrida
public class CalcularFrete{
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		float fixo;
		float transp;
		float trecho;
		
		System.out.println("Digite o peso do produto");
		float peso = sc.nextFloat();
		
		System.out.println("Digite a distância que irá percorrer");
		float dist = sc.nextFloat();
		
		if (dist >= 500)
		{
			transp = 0.20F;
			fixo = 50.0F;
			trecho = dist / 300;
									
			System.out.println("Valor total do Frete");
			System.out.println(peso *(Math.round(trecho) * transp) + fixo);
		}
		else if (dist < 500 && dist >= 100)
		{
			transp = 0.04F;
			fixo = 30.0F;
			trecho = dist / 50;
						
			System.out.println("Valor total do Frete");
			System.out.println(peso *(Math.round(trecho) * transp) + fixo);
			
		}
		else if (dist < 100)
		{
			transp = 0.04F;
			fixo = 10.0F;
			trecho = dist / 25;
			
			System.out.println("Valor total do frete é");
			System.out.println(peso *(Math.round(trecho) * transp) + fixo);
		}
	}	
}
