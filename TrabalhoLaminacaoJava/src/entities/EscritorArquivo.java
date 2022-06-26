package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorArquivo {

	public static void escritaArquivo(String path, List<String> log) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
			for (String item : log) {
				bw.write(item);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
