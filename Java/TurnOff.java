package br.com.fiap.nac;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class TurnOff {

	public static void main(String Args[]){
		String [] beacon_nome=  {"Magenta","Azul","Branco"};
		double [][] beacon_prec_vol_pes= {{45.99,0.013,0.05},{45.99,0.013,0.05},{45.99,0.013,0.05}};
		String [] cd_nome = {"São Paulo/SP","Recife/PE","Manaus/AM"};
		double [][] cd_lt_lg = {{-23.3251,-46.3810},{-8.0314,-34.5252},{-3.0607,-60.0130}};
		String [] fabrica ={"Brasília/DF"};
		double []fab_lt_lg= {-15.4647,-47.5547};
		String [][] cliente = {{"T-Systems","Rio Branco/AC"},{"FIAP","Andorinha/BA"},{"7Comm","Porto Alegre/RS"}};
		double [][] cli_lt_lg={{-09.5829,-67.4836},{-10.2041,-39.5008},{-30.0159,-51.1348}};
		String cli="";
		double []vl_total= new double[3];
		double soma=0, peso_total=0;
		int[] qtd_prod= new int [3];
		int[] qtd_prod_estoque= {50,50,50};
		boolean resp=false;
		NumberFormat df = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		DecimalFormat pf= new DecimalFormat("0.000");
		

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
		System.out.println("Escolha o cliente que irá realizar o pedido \nOpções: \n 0 - T-System \n 1 - FIAP \n 2 - 7Comm");
		int cliopt =entrada.nextInt();
		
		if(cliopt==0){
			cli=cliente[0][0];
		}if(cliopt==1){
			cli=cliente[1][0];
		}if(cliopt==2){
			cli=cliente[2][0];
		}
		
		//Pedido: Quantidade por modelo
		do{

			
		
		//Laço para obter quantos beancos de cada cor serão "comprados"
		for(int i=0;i<3;++i){
			System.out.println("Quantidade do "+beacon_nome[i]);
			qtd_prod[i]=entrada.nextInt();	
			vl_total[i]=qtd_prod[i]*beacon_prec_vol_pes[i][0];
			System.out.println(df.format(vl_total[i]));
			
			
			
			//Somo para obter o preço totaol de beacons Solicitados
			soma=vl_total[i]+soma;
			
			
			
			
			//Soma peso total do pedido
			peso_total=peso_total+(qtd_prod[i]*beacon_prec_vol_pes[i][2]);
		}
		
		
		
		
		
		// Valida se existe produto no pedido
		if(soma>0){
		
		//Instancia o método para retornar a distancia e Centro de distribuição mais próximo
		double dist=centroMaisProximo(cliopt,cli_lt_lg,cd_lt_lg,cd_nome);
		System.out.println("Distância em km: "+Math.round(dist));
		
		
		
		//Instancia o método para retornar o valor do frete
		double frete = valorFrete(peso_total,dist);
		
		
		
		
			

		//Teste de dados do pedido(Quantidade solicitado por produto, valor total dos produtos e peso total)	
		System.out.println("===========Nota Fiscal===========");
		System.out.println("Cliente: "+cli);
		System.out.println("CPF/CNPJ: XXXXXXXXXX-XX");
		System.out.println("Quantidade "+beacon_nome[0]+": "+qtd_prod[0]);
		System.out.println("Quantidade "+beacon_nome[1]+": "+qtd_prod[1]);	
		System.out.println("Quantidade "+beacon_nome[2]+": "+qtd_prod[2]);	
		System.out.println("Valor total do pedido: "+df.format(soma)+" R$");
		System.out.println("Peso total do pedido:"+pf.format(peso_total)+" Kg");
		System.out.println("Valor do frete: "+df.format(frete));
		
		}else if(soma<1){
			System.out.println("Pedido não existe produto(s)");
			
			
			
			//Valida se quer tentar novamente
			System.out.println("Deseja tentar novamente?\n S = Sim \n N = Não");
			char op = entrada.next().toUpperCase().charAt(0);
			if(op=='S'){
				resp=true;
			}if(op=='N'){
				resp=false;
			}
		}
		}while(resp==true);
	}
	
	
	//Metodo para retornar o centro de distribuição mais próximo
	public static double centroMaisProximo(int cliopt, double cli_lt_lg[][],double cd_lt_lg[][],String cd_nome[] ){
		double dist[]= new double[3];
		double aux;
		String auxcidade;
		
		
		
		//Faz a conta entre "CD" e Destino
		for(int i=0;i<3;++i){
			dist[i]= (Math.sqrt((cli_lt_lg[cliopt][0]-cd_lt_lg[i][0])*(cli_lt_lg[cliopt][0]-cd_lt_lg[i][0]) +(cli_lt_lg[cliopt][1]-cd_lt_lg[i][1])*(cli_lt_lg[cliopt][1]-cd_lt_lg[i][1])))*109;
			System.out.println("CD "+cd_nome[i]+"\nDistância: "+Math.round(dist[i])+" Km");
		}
		
		
		
		//Faz a ordenação e retorna o "CD" mais próximo do Destino
		for(int i=0;i<2;++i){
			if(dist[i]>dist[i+1]){
				auxcidade=cd_nome[i+1];
				cd_nome[i+1]=cd_nome[i];
				cd_nome[i]=auxcidade;
				aux=dist[i+1];
				dist[i+1]=dist[i];
				dist[i]=aux;

			}


		}
		System.out.println("Centro de Distribuição mais próximo: "+cd_nome[0]);
		return dist[0];
	}


	// Metodo para retornar valor do Frete
	public static double valorFrete(double peso_total, double dist){
		float fixo;
		float transp;
		double trecho,frete=0;
		
		if (dist >= 500)
		{
			transp = 0.20F;
			fixo = 50.0F;
			trecho = dist / 300;

			frete=(peso_total *(Math.round(trecho) * transp) + fixo);
		}
		else if (dist < 500 && dist >= 100)
		{
			transp = 0.04F;
			fixo = 30.0F;
			trecho = dist / 50;

			frete=(peso_total *(Math.round(trecho) * transp) + fixo);

		}
		else if (dist < 100)
		{
			transp = 0.04F;
			fixo = 10.0F;
			trecho = dist / 25;

			frete=(peso_total *(Math.round(trecho) * transp) + fixo);
		}
		return frete;
	}
	
	public static String getExcecao(Exception e){
		if (e instanceof java.util.InputMismatchException){
			return "Valor digitado inválido";
		}else if (e instanceof NumberFormatException){
			return "Número inválido";
		}else{
			return "Erro desconhecido";
		}
	}
	
}