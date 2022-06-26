package application;

import java.util.ArrayList;
import java.util.List;

import algorithms.*;
import entities.Lamina;
import entities.LeitorArquivo;

public class Tests {
//"src\\LaminacaoTeste4.txt"
	public static void main(String args[]) {
		//testaAlgGuloso();
		//testaBackTracking();
		testaProgDinamica();
	}

	public static void testaBackTracking() {
		List<Lamina> listaLamina = LeitorArquivo.leituraArquivoLista("src\\LaminacaoTeste4.txt");
		System.out.println("Procurando valor via BackTracking");
		int c1 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao3(), 3, listaLamina);

		int c2 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao2(), 2, listaLamina);

		int c3 = Backtracking.backtrackingRecursivo(listaLamina.get(0).getLargura(),
				listaLamina.get(0).getCustoReducao1(), 1, listaLamina);
		System.out.print("Melhor valor encontrado via backtracking:");
		if (c3 <= c2 && c3 <= c1) {
			System.out.println(c3);
		} else if (c2 <= c1) {
			System.out.println(c2);
		} else {
			System.out.println(c1);
		}
	}

	public static void testaAlgGuloso() {

		Lamina teste = LeitorArquivo.leituraArquivo("src\\LaminacaoTeste4.txt");
		System.out.println("Iniciando redução da lâmina de largura = " + teste.getLargura());

		while (teste.getLargura() > 4) {
			if (teste.getCustoPorMM3() != null && teste.getCustoReducao3() != null
					&& teste.getCustoPorMM3() <= teste.getCustoPorMM2()
					&& teste.getCustoPorMM3() <= teste.getCustoPorMM1()) {
				teste.reduzirLamina(3);
				System.out.println("Lamina reduziu 3MM, custo total da reducao = " + teste.getCustoTotal());
				System.out.println("Nova largura = " + teste.getLargura());
			} else if (teste.getCustoPorMM2() != null && teste.getCustoReducao2() != null
					&& teste.getCustoPorMM2() <= teste.getCustoPorMM1()) {
				teste.reduzirLamina(2);
				System.out.println("Lamina reduziu 2MM, custo total da reducao = " + teste.getCustoTotal());
				System.out.println("Nova largura = " + teste.getLargura());
			} else {
				teste.reduzirLamina(1);
				System.out.println("Lamina reduziu 1MM, custo total da reducao = " + teste.getCustoTotal());
				System.out.println("Nova largura = " + teste.getLargura());
			}
		}
		System.out.println("Lâmina reduzida com sucesso!");
		System.out.println("Custo total da operação = " + teste.getCustoTotal());
		System.out.println("Número de reduções realizadas = " + teste.getNumeroReducoes());

	}

	public static void testaReducao() {

		List<Lamina> teste = new ArrayList<>();

		teste.add(new Lamina(10, 2, 4, 6));
		teste.add(new Lamina(9, 8, 10, 12));
		teste.add(new Lamina(8, 14, 16, 18));
		teste.add(new Lamina(7, 20, 22, 24));

		Lamina lamina = new Lamina(11, 1, 2, 3, teste);

		lamina.reduzirLamina(3);
		System.out.println("CustoTotal:" + lamina.getCustoTotal());
		System.out.println(lamina.getLargura());
		System.out.println(lamina.getCustoPorMM1());
		System.out.println(lamina.getCustoPorMM2());
		System.out.println(lamina.getCustoPorMM3());
		System.out.println(lamina.getCustoReducao1());
		System.out.println(lamina.getCustoReducao2());
		System.out.println(lamina.getCustoReducao3());
	}

	public static void testaProgDinamica() {

		List<Lamina> listaLaminas = LeitorArquivo.leituraArquivoLista("src\\LaminacaoTeste1.txt");
		Integer tabelaDinamica[][] = new Integer[listaLaminas.size()][listaLaminas.size()];
		Integer vetorColunas[] = new Integer[listaLaminas.size()];
		Integer vetorLinhas[] = new Integer[listaLaminas.size()];

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
		for (int i = 0; i < tabelaDinamica.length; i++) {
			for (int j = 0; j < tabelaDinamica.length; j++) {
				if (tabelaDinamica[i][j] != 2147483647)
					System.out.print(tabelaDinamica[i][j] + "   ");
				else
					System.out.print("X    ");
			}
			System.out.println();
		}

	}

}
