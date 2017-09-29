/*
 * 				PRIORIZAR ENTREGA
 * Este codigo tem como objetivo, priorizar os pedidos que tem sua prioridade
 * baseada em dois tipos: frete & prazo.
 * 
 * Basicamente, ele pega uma matriz chumbada e guarda em duas outras matrizes dependendo
 * da verificação realizada, se o pedido for FRETE ele vai para a matriz priorizado, se nao
 * ele vai para a matriz naoPriorizados. No final é exibido os produtos que são priorizados e não.
 */
public class Priorizacao {
	public static void main(String[] args) {

		// VALORES CHUMBADOS
		String s[][] = new String[][] { { "ZXC", "Sabao", "prazo" }, { "XYZ", "Colchao", "frete" },
				{ "ABC", "Salame", "prazo" } };

		String[][] priorizados = new String[s.length][];
		String[][] naoPriorizados = new String[s.length][];

		//VARIAVEIS PARA O TAMANHO DAS MATRIZES DE PRIORIDADE
		int iPrior = 0, iNPrior = 0;	

		for (int x = 0; x < s.length; x++) {

			System.out.println("PEDIDO " + (x + 1));

			//	Caso o pedido tenha prioridade ao frete
			if (s[x][2].equals("frete")) {
				priorizados[iPrior] = s[x];	//	Atribui o conteudo á matriz de prioridade
				iPrior++;	//	Aumenta o tamanho da matriz de pedidos priorizados
				
			} else {	//	Se ter prioridade á prazo
				
				naoPriorizados[iNPrior] = s[x];
				iNPrior++;
			}
			System.out.println();
		}
		
		System.out.println("NÃO PRIOZADOS: ");
		
		for (int x = 0; x < iNPrior; x++) {	// Varre a matriz baseado no tamanho das incrementacoes
			for (int y = 0; y < s[x].length; y++) {	//	Varre a matriz baseado no tamanho das colunas dos valores chumbados, pode colocar como 2/3 aki
				System.out.print(naoPriorizados[x][y] + " | ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("PRIORIZADOS: ");
		for (int x = 0; x < iPrior; x++) {
			for (int y = 0; y < s[x].length; y++) {
				System.out.print(priorizados[x][y] + " | ");
			}
			System.out.println();
		}
	}
}
