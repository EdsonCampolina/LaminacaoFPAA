package application;

import java.util.ArrayList;
import java.util.List;

import algorithms.AlgoritmoGuloso;
import algorithms.Backtracking;
import algorithms.ProgramacaoDinamica;
import entities.EscritorArquivo;

public class Program {

	public static void main(String args[]) {

		for (int i = 1; i <= 4; i++) {
			List<String> log = new ArrayList<>();
			// Declarando caminhos para leitura e escrita de dados em arquivos
			String path = "src\\LaminacaoTeste" + i + ".txt";
			String pathEscrita = "src\\LaminacaoTeste" + i + "Execucao.txt";
			System.out.println("Començando a execução dos casos de teste dos algoritmos contidos em " + path);
			// Comecando a execucao dos algoritmos
			Long inicioGuloso = System.nanoTime();
			System.out.println("Iniciando algoritmo guloso!");
			AlgoritmoGuloso.algoritmoGuloso(path, log);
			System.out.println("Algoritmo guloso finalizado!");
			Long inicioProgramacaoDinamica = System.nanoTime();
			System.out.println("Iniciando programação dinâmica!");
			ProgramacaoDinamica.programacaoDinamica(path, log);
			System.out.println("Finalizando programação dinâmica!");
			Long inicioBacktracking = System.nanoTime();
			System.out.println("Inicializando backtracking!");
			Backtracking.backtracking(path, log);
			System.out.println("Finalizando backtracking!");
			Long finalBacktracking = System.nanoTime();
			// Adicionando o tempo de execucao de cada algoritmo no arquivo que será gerado
			log.add("Tempo de execução do algoritmo guloso em nanossegundos = "
					+ (inicioProgramacaoDinamica - inicioGuloso));
			log.add("Tempo de execução da programação dinâmica em nanossegundos = "
					+ (inicioBacktracking - inicioProgramacaoDinamica));
			log.add("Tempo de execução do backtracking em nanossegundos = " + (finalBacktracking - inicioBacktracking));
			// Escrevendo resultados no arquivo
			EscritorArquivo.escritaArquivo(pathEscrita, log);
			System.out.println("Execução finalizada!");
		}
	}
}
