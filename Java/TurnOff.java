package br.com.fiap.nac;

import java.util.Scanner;

public class TurnOff {

	public static void main(String Args[]){
		String [] beacon_nome=  {"Magenta","Azul","Branco"};
		double [][] beacon_prec_vol_pes= {{45.99,0.013,0.05},{45.99,0.013,0.05},{45.99,0.013,0.05}};
		String [] cd_nome = {"São Paulo/SP","Recife/PE","Manaus/AM"};
		double [][] cd_lt_lg = {{-23.3251,-46.3810},{-8.0314,-34.5252},{-3.0607,-60.0130}};
		String [] fabrica ={"Brasília/DF"};
		double []fab_lt_lg= {-15.4647,-47.5547};
		double []vl_total= new double[3];
		double soma=0, peso_total=0;
		int[] qtd_prod= new int [3];
		double dist=0;

		Scanner entrada = new Scanner(System.in);		

		// Teste para exibir valores fixados
		for(int i=0;i<3;++i){
			System.out.println("Modelo: "+beacon_nome[i]);
			for(int j=0;j<3;++j){
				if(j==0){
					System.out.println("Preço: "+beacon_prec_vol_pes[i][j]+" R$");
				}if(j==1){
					System.out.println("Volume: "+beacon_prec_vol_pes[i][j]+" m³");
				}if(j==2){
					System.out.println("Peso: "+beacon_prec_vol_pes[i][j]+" Kg");
				}
			}

			System.out.println("================================");
		}
		// Pedido
		System.out.println("Nome do cliente: ");
		String cliente = entrada.next();
		//Pedido: Quantidade por modelo
		for(int i=0;i<3;++i){
			System.out.println("Quantidade do "+beacon_nome[i]);
			qtd_prod[i]=entrada.nextInt();	
			vl_total[i]=qtd_prod[i]*beacon_prec_vol_pes[i][0];
			System.out.println(vl_total[i]);
			soma=vl_total[i]+soma;
			peso_total=peso_total+(qtd_prod[i]*beacon_prec_vol_pes[i][2]);
			double frete = valorFrete(peso_total,dist);
		}
		//Teste de dados do pedido(Quantidade solicitado por produto, valor total dos produtos e peso total)
		System.out.println("Nome "+cliente);
		System.out.println("Quantidade "+beacon_nome[0]+": "+qtd_prod[0]);
		System.out.println("Quantidade "+beacon_nome[1]+": "+qtd_prod[1]);	
		System.out.println("Quantidade "+beacon_nome[2]+": "+qtd_prod[2]);	
		System.out.println("Valor total do pedido: "+soma+" R$");
		System.out.println("Peso total do pedidot: "+peso_total+" Kg");

	}
	
	//Metodo para retornar a distância 
	public static double distancia(){
		return 0;
	}

	// Metodo para retornar valor do Frete
	public static double valorFrete(double peso_total, double dist){
		float fixo;
		float transp;
		double trecho;

		if (dist >= 500)
		{
			transp = 0.20F;
			fixo = 50.0F;
			trecho = dist / 300;

			System.out.println("Valor total do Frete");
			System.out.println(peso_total *(Math.round(trecho) * transp) + fixo);
		}
		else if (dist < 500 && dist >= 100)
		{
			transp = 0.04F;
			fixo = 30.0F;
			trecho = dist / 50;

			System.out.println("Valor total do Frete");
			System.out.println(peso_total *(Math.round(trecho) * transp) + fixo);

		}
		else if (dist < 100)
		{
			transp = 0.04F;
			fixo = 10.0F;
			trecho = dist / 25;

			System.out.println("Valor total do frete é");
			System.out.println(peso_total *(Math.round(trecho) * transp) + fixo);
			}
		return 0;
		}
	}

