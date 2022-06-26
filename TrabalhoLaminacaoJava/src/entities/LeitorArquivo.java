package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivo {

	public static Lamina leituraArquivo(String path) {
		boolean isPrimeiraLamina = true;
		File file = new File(path);
		Scanner sc = null;
		List<Lamina> listLamina = new ArrayList<>();
		Lamina laminaRetorno = null;
		try {
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String laminaInit = sc.nextLine().replace("  ", " ");
				String lamina[] = laminaInit.split(" ");
				Integer largura;
				Integer custoReducao1;
				Integer custoReducao2;
				Integer custoReducao3;

				if (Integer.parseInt(lamina[0]) > 6) {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = Integer.parseInt(lamina[2]);
					custoReducao3 = Integer.parseInt(lamina[3]);

				} else if (Integer.parseInt(lamina[0]) == 6) {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = Integer.parseInt(lamina[2]);
					custoReducao3 = null;
				} else {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = null;
					custoReducao3 = null;
				}
				if (!isPrimeiraLamina) {
					listLamina.add(new Lamina(largura, custoReducao1, custoReducao2, custoReducao3));
				} else {
					laminaRetorno = new Lamina(largura, custoReducao1, custoReducao2, custoReducao3);
					isPrimeiraLamina = false;
				}

			}

		} catch (FileNotFoundException ex) {
			System.out.println("Erro ao abrir o arquivo: " + ex.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
			System.out.println("Arquivo foi fechado!");
		}
		laminaRetorno.setTabelaValores(listLamina);
		return laminaRetorno;
	}

	public static List<Lamina> leituraArquivoLista(String path) {

		File file = new File(path);
		Scanner sc = null;
		List<Lamina> listLamina = new ArrayList<>();
		try {
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String laminaInit = sc.nextLine().replace("  ", " ");
				String lamina[] = laminaInit.split(" ");
				Integer largura;
				Integer custoReducao1;
				Integer custoReducao2;
				Integer custoReducao3;
				if (Integer.parseInt(lamina[0]) > 6) {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = Integer.parseInt(lamina[2]);
					custoReducao3 = Integer.parseInt(lamina[3]);

				} else if (Integer.parseInt(lamina[0]) == 6) {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = Integer.parseInt(lamina[2]);
					custoReducao3 = null;
				} else {
					largura = Integer.parseInt(lamina[0]);
					custoReducao1 = Integer.parseInt(lamina[1]);
					custoReducao2 = null;
					custoReducao3 = null;
				}
				listLamina.add(new Lamina(largura, custoReducao1, custoReducao2, custoReducao3));

			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro ao abrir o arquivo: " + ex.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
			System.out.println("Arquivo foi fechado!");
		}
		return listLamina;
	}

}
