package algorithms;

import java.util.List;

import entities.Lamina;
import entities.LeitorArquivo;

public class ProgramacaoDinamica {

	public static void programacaoDinamica(String path, List<String> log) {
		List<Lamina> listaLaminas = LeitorArquivo.leituraArquivoLista(path);
		Integer tabelaDinamica[][] = new Integer[listaLaminas.size()][listaLaminas.size()];
		Integer vetorColunas[] = new Integer[listaLaminas.size()];
		Integer vetorLinhas[] = new Integer[listaLaminas.size()];
		log.add("Iniciando execução da programação dinâmica!");
		for (int i = 0; i < listaLaminas.size(); i++) {
			vetorColunas[i] = listaLaminas.get(i).getLargura() - 1;
			vetorLinhas[i] = listaLaminas.get(i).getLargura();
		}

		for (int linha = 0; linha < listaLaminas.size(); linha++) {
			Lamina laminaParametro = Lamina.encontraLaminaPorLargura(listaLaminas, vetorLinhas[linha]);
			for (int coluna = 0; coluna < listaLaminas.size(); coluna++) {
				if (linha == 0) {

					if (vetorLinhas[linha] == vetorColunas[coluna] + 1) {
						tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao1();
					} else if (vetorLinhas[linha] == vetorColunas[coluna] + 2) {
						tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao2();
					} else if (vetorLinhas[linha] == vetorColunas[coluna] + 3) {
						tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao3();
					} else {
						tabelaDinamica[linha][coluna] = Integer.MAX_VALUE;
					}
				} else {
					if (vetorColunas[coluna] >= vetorLinhas[linha]) {
						tabelaDinamica[linha][coluna] = tabelaDinamica[linha - 1][coluna];
					} else {
						int valorParametro = tabelaDinamica[linha - 1][linha - 1];
						if ((vetorLinhas[linha] == vetorColunas[coluna] + 1) && (valorParametro
								+ laminaParametro.getCustoReducao1() < tabelaDinamica[linha - 1][coluna])) {

							tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao1() + valorParametro;

						} else if ((vetorLinhas[linha] == vetorColunas[coluna] + 2) && (valorParametro
								+ laminaParametro.getCustoReducao2() < tabelaDinamica[linha - 1][coluna])) {

							tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao2() + valorParametro;

						} else if ((vetorLinhas[linha] == vetorColunas[coluna] + 3) && (valorParametro
								+ laminaParametro.getCustoReducao3() < tabelaDinamica[linha - 1][coluna])) {

							tabelaDinamica[linha][coluna] = laminaParametro.getCustoReducao3() + valorParametro;

						} else {

							tabelaDinamica[linha][coluna] = tabelaDinamica[linha - 1][coluna];

						}

					}

				}
			}
		}
		// IMPRESSAO TABELA
		String linha = "";
		for (int i = 0; i < tabelaDinamica.length; i++) {
			linha = "";
			for (int j = 0; j < tabelaDinamica.length; j++) {
				if (tabelaDinamica[i][j] != 2147483647)
					linha += tabelaDinamica[i][j] + "   ";
				else
					linha += "X    ";
			}
			log.add(linha);
			log.add("");
		}
		log.add("Melhor valor via programação dinâmica é " + tabelaDinamica[listaLaminas.size()-1][listaLaminas.size()-1]);
		log.add("Programação dinâmica finalizada!");
		log.add("");
		log.add("");
	}
}
