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
		int frete_fab_cd=3;
		String cli="";
		int qtTotal=0;
		double []vl_total= new double[3];
		double soma=0, peso_total=0;
		int[] qtd_prod= new int [3];
		int qtd_prod_producao=0;
		int[] qtd_prod_estoque= {50,50,50};
		int qtd_mp= 100;
		boolean produzir=false;
		int prazo_prod=0;
		int prazo=0;
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

			System.out.println("==============Clientes--TurnOff==================");
		}
		// Pedido
		System.out.println("Escolha o cliente que irá realizar o pedido \nOpções: \n 1 - T-System \n 2 - FIAP \n 3 - 7Comm");
		int cliopt =entrada.nextInt();

		if(cliopt==1){
			cli=cliente[0][0];
		}if(cliopt==2){
			cli=cliente[1][0];
		}if(cliopt==3){
			cli=cliente[2][0];
		}

		//Pedido: Quantidade por modelo
		do{



			//Laço para obter quantos beancos de cada cor serão "comprados"
			for(int i=0;i<3;++i){
				System.out.println("Quantidade do "+beacon_nome[i]);
				qtd_prod[i]=entrada.nextInt();	
				vl_total[i]=qtd_prod[i]*beacon_prec_vol_pes[i][0];
				qtTotal=qtd_prod[i]+qtTotal;


				//Somo para obter o preço totaol de beacons Solicitados
				soma=vl_total[i]+soma;




				//Soma peso total do pedido
				peso_total=peso_total+(qtd_prod[i]*beacon_prec_vol_pes[i][2]);

				//Condição para soma de fabricação 
				if(qtd_prod[i]>50) {
					qtd_prod_producao=qtd_prod[i]-50+qtd_prod_producao;
					produzir=true;
				}
			}

			double descFrete =descFrete (qtTotal);
			int descPrazo=descPrazo (qtTotal);

			// Valida se existe produto no pedido
			if(soma>0){
				System.out.println("===========Relatório-TurnOff===========");
				System.out.println();
				
				for(int i=0;i<3;++i){
					System.out.println("Quantidade do "+beacon_nome[i]+": "+qtd_prod[i]);
					System.out.println("Preço total do item: "+df.format(vl_total[i]));
					System.out.println();
				}
				//Instancia o método para retornar a distancia e Centro de distribuição mais próximo
				
				System.out.println("Comparação entre a distância do cliente e o Centro de Distribuição(CD):");
				double dist=centroMaisProximo(cliopt,cli_lt_lg,cd_lt_lg,cd_nome);
				System.out.println("Distância: "+Math.round(dist));
				System.out.println();
				System.out.println();
				
				prazo =prazoCDCli(dist,prazo);
				if(produzir==false) {
					System.out.println("Prazo máximo de entrega, sem a necessidade de produção:");
					prazo=descPrazo+prazo;
					System.out.println("Prazo sem prioridade: "+(prazo));
					if(prazo!=descPrazo+prazo) {
						prazo=descPrazo+prazo;
						System.out.println("Prazo com prioridade: "+(prazo));
					}

				}

				//Condição para acionar metodo de produção

				if(produzir==true) {
					System.out.println("Prazo máximo de entrega, com a necessidade de produção:");
					prazo_prod=producao(qtd_prod_producao,qtd_mp,prazo,frete_fab_cd);
					System.out.println("Prazo de producao sem prioridade: "+prazo_prod);
					prazo=prazo_prod+descPrazo;
					if(prazo_prod!=prazo_prod) {
						System.out.println("Prazo de producao com prioridade: "+(prazo_prod+descPrazo));
					}
					
				}


				//Instancia o método para retornar o valor do frete
				double frete = valorFrete(peso_total,dist);

				System.out.println("Valor do frete sem prioridade: "+df.format(frete));
				if(frete!=frete-frete*descFrete) {
				System.out.println("Valor do frete com prioridade: "+df.format(frete-frete*descFrete));
				frete=frete-frete*descFrete;
				}





				//Teste de dados do pedido(Quantidade solicitado por produto, valor total dos produtos e peso total)	
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("===========Nota Fiscal-TurnOff===========");
				System.out.println("Cliente: "+cli);
				System.out.println("CPF/CNPJ: XXXXXXXXXX-XX");
				System.out.println("Quantidade "+beacon_nome[0]+": "+qtd_prod[0]);
				System.out.println("Quantidade "+beacon_nome[1]+": "+qtd_prod[1]);	
				System.out.println("Quantidade "+beacon_nome[2]+": "+qtd_prod[2]);	
				System.out.println("Número total de produtos: "+qtTotal);	
				System.out.println("Valor total do pedido: "+df.format(soma));
				System.out.println("Peso total do pedido: "+pf.format(peso_total)+" Kg");
				System.out.println("Prazo máxima para entrega: "+(prazo)+" Dias");
				System.out.println("Valor do frete: "+df.format(frete));

				
				
				
				
			}else if(soma<1){
				System.out.println("Cliente escolhido inválido ou não existe produto(s)");



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
		double aux=0;
		String auxcidade="";



		//Faz a conta entre "CD" e Destino
		for(int i=0;i<3;++i){
			dist[i]= (Math.sqrt((cli_lt_lg[cliopt-1][0]-cd_lt_lg[i][0])*(cli_lt_lg[cliopt-1][0]-cd_lt_lg[i][0]) +(cli_lt_lg[cliopt-1][1]-cd_lt_lg[i][1])*(cli_lt_lg[cliopt-1][1]-cd_lt_lg[i][1])))*109;
			System.out.println("CD "+cd_nome[i]+"\nDistância: "+Math.round(dist[i])+" Km");
		}



		//Faz a ordenação e retorna o "CD" mais próximo do Destino
		for(int i=0;i<3;++i) {
			for(int j=0;j<2;++j){
				if(dist[j]>dist[j+1]){
					aux=dist[j];
					dist[j] = dist[j+1];
					dist[j+1] = aux;
					
					
					auxcidade=cd_nome[j];
					cd_nome[j]=cd_nome[j+1];
					cd_nome[j+1]=auxcidade;
					
				}
		}
		


		}
		System.out.println();
		System.out.println("Conforme a verificação o Centro de Distribuição mais próximo é: "+cd_nome[0]);
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

	// Metodo que adiciona os dias para produzir os beacons faltantes do pedido
	public static int producao(int qtd_prod_producao,int qtd_mp,int prazo, int frete_fab_cd) {
		double prazo_prod=0;
		double	prazo_mp=0;

		//Caso seja necessário produzir no máximo 50 beacons, que é o limite de fabricação por dia
		if(qtd_prod_producao<=50){




			//necessário 3 materias primas para produzir 1 beacon
			//Caso falte materia prima para produzir ele fica negativo e entra na condição
			prazo_mp=100-(qtd_prod_producao*3);
			if(prazo_mp<0) {



				//A materia prima demora 2 dias para cada 80 itens que são pedidos para fornecer
				//caso passe de 80 soma +2 dias para cada pedido entre 80
				prazo_mp=Math.ceil(((prazo_mp)*-1)/80)*2;
				prazo_prod=1+prazo+prazo_mp+frete_fab_cd;	
			}else {
				prazo_prod=1+prazo+frete_fab_cd;
			}
			//Caso seja necessário produzir mais que o limite diário de 50 beacons
		}else if(qtd_prod_producao>50){
			prazo_mp=100-(qtd_prod_producao*3);
			if(prazo_mp<0) {
				prazo_mp=Math.ceil(((prazo_mp)*-1)/80)*2;
				prazo_prod=Math.ceil(qtd_prod_producao/50)+prazo+prazo_mp;
			}else {
				prazo_prod=Math.round(qtd_prod_producao)+prazo+frete_fab_cd;	
			}


		}

		return (int) prazo_prod;
	}
	// Metodo para calcular o prazo entre CD e Destino(Cliente) 
	//sem necessidade de produçao(movimentação entre fábrica e CD)
	public static int prazoCDCli(double dist,int prazo){
		if(dist<=100) {
			prazo=2;
		}else if(dist>100) {
			prazo=(int)(Math.ceil(dist/100)*2);
		}

		return prazo;
	}

	public static double descFrete (double qtTotal) {

		if (qtTotal <=50) {  //  Se a quantidade total de produtos for igual ou maior que 10
			return 0.10;        //  Retorna 10% de desconto/reajuste
		}
		return 0;

	}

	public static int descPrazo (int qtTotal) {
		if (qtTotal >50 && qtTotal <=90 ) {  //  Se a quantidade total de produtos for igual ou maior que 10
			return -1;        //  Retorna 10% de desconto/reajuste
		}

		return 0;

	}



}