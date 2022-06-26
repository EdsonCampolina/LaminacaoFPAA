package algorithms;

import java.util.List;

import entities.Lamina;
import entities.LeitorArquivo;

public class Backtracking {

	public static void backtracking(String path, List<String> log) {
		List<Lamina> listaLamina = LeitorArquivo.leituraArquivoLista(path);
		log.add("Procurando valor via BackTracking");
		int c1 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao3(), 3, listaLamina);

		int c2 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao2(), 2, listaLamina);

		int c3 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao1(), 1, listaLamina);
		if (c3 <= c2 && c3 <= c1) {
			log.add("Melhor valor encontrado via backtracking: " + c3);
		} else if (c2 <= c1) {
			log.add("Melhor valor encontrado via backtracking: " + c2);
		} else {
			log.add("Melhor valor encontrado via backtracking: " + c1);
		}
		log.add("");
		log.add("");
		log.add("");
	}

	// Este método poderia ser private, entretanto daria erro nos prgramas de teste
	public static int backtrackingRecursivo(int tamanho, int custo, int reducao, List<Lamina> lista) {
		int custo1 = custo;
		int custo2 = custo;
		int custo3 = custo;
		int novoTamanho = tamanho - reducao;
		Lamina lamina = new Lamina();
		if (novoTamanho > 4) {
			lamina = Lamina.encontraLaminaPorLargura(lista, novoTamanho);
		}
		if (novoTamanho > 6) {
			custo3 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao3() + custo, 3, lista);
			custo2 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao2() + custo, 2, lista);
			custo1 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao1() + custo, 1, lista);
			if (custo3 <= custo2 && custo3 <= custo1)
				return custo3;
			else if (custo2 <= custo1)
				return custo2;
			return custo1;
		} else if (novoTamanho == 6) {
			custo2 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao2() + custo, 2, lista);
			custo1 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao1() + custo, 1, lista);
			if (custo2 <= custo1)
				return custo2;
			return custo1;
		} else if (novoTamanho == 5) {
			custo1 = backtrackingRecursivo(novoTamanho, lamina.getCustoReducao1() + custo, 1, lista);
			return custo1;
		}
		return custo;

	}

}
