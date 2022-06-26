package algorithms;

import java.util.List;

import entities.Lamina;
import entities.LeitorArquivo;

public class AlgoritmoGuloso {

	public static void algoritmoGuloso(String path, List<String> log) {
		Lamina laminaAlgoritmo = LeitorArquivo.leituraArquivo(path);
		log.add("Incio alogritmo guloso!");
		log.add("Iniciando redução da lâmina de largura = " + laminaAlgoritmo.getLargura());
		while (laminaAlgoritmo.getLargura() > 4) {
			if (laminaAlgoritmo.getCustoPorMM3() != null && laminaAlgoritmo.getCustoReducao3() != null
					&& laminaAlgoritmo.getCustoPorMM3() <= laminaAlgoritmo.getCustoPorMM2()
					&& laminaAlgoritmo.getCustoPorMM3() <= laminaAlgoritmo.getCustoPorMM1()) {
				laminaAlgoritmo.reduzirLamina(3);
				log.add("Lamina reduziu 3MM, custo total da reducao = " + laminaAlgoritmo.getCustoTotal());
				log.add("Nova largura = " + laminaAlgoritmo.getLargura());
			} else if (laminaAlgoritmo.getCustoPorMM2() != null && laminaAlgoritmo.getCustoReducao2() != null
					&& laminaAlgoritmo.getCustoPorMM2() <= laminaAlgoritmo.getCustoPorMM1()) {
				laminaAlgoritmo.reduzirLamina(2);
				log.add("Lamina reduziu 2MM, custo total da reducao = " + laminaAlgoritmo.getCustoTotal());
				log.add("Nova largura = " + laminaAlgoritmo.getLargura());
			} else {
				laminaAlgoritmo.reduzirLamina(1);
				log.add("Lamina reduziu 1MM, custo total da reducao = " + laminaAlgoritmo.getCustoTotal());
				log.add("Nova largura = " + laminaAlgoritmo.getLargura());
			}
		}
		log.add("Lâmina reduzida com sucesso!");
		log.add("Custo total da operação = " + laminaAlgoritmo.getCustoTotal());
		log.add("Número de reduções realizadas = " + laminaAlgoritmo.getNumeroReducoes());
		log.add("Fim algoritmo guloso!");
		log.add("");
		log.add("");
		log.add("");
	}
}
