package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Lamina {

	private int Largura;

	private Integer custoReducao1 = null;
	private Integer custoReducao2 = null;
	private Integer custoReducao3 = null;

	private Double custoPorMM1 = null;
	private Double custoPorMM2 = null;
	private Double custoPorMM3 = null;

	private List<Lamina> tabelaValores = new ArrayList<>();

	private int custoTotal = 0;
	private int numeroReducoes = 0;

	public Lamina() {

	}

	public Lamina(Integer Largura, Integer custoReducao1, Integer custoReducao2, Integer custoReducao3,
			List<Lamina> tabela) {
		this.Largura = Largura;
		this.custoReducao1 = custoReducao1;
		this.custoReducao2 = custoReducao2;
		this.custoReducao3 = custoReducao3;
		this.tabelaValores = tabela;
		calculaCustosPorMM();

	}

	public Lamina(Integer Largura, Integer custoReducao1, Integer custoReducao2, Integer custoReducao3) {
		this.Largura = Largura;
		this.custoReducao1 = custoReducao1;
		this.custoReducao2 = custoReducao2;
		this.custoReducao3 = custoReducao3;
		calculaCustosPorMM();

	}

	public Lamina clone() {
		Lamina clone = new Lamina(this.Largura, this.custoReducao1, this.custoReducao2, this.custoReducao3,
				new ArrayList<Lamina>(this.tabelaValores));
		return clone;
	}

	public void reduzirLamina(int tamanho) {
		switch (tamanho) {
		case 1:
			custoTotal += custoReducao1;
			break;
		case 2:
			custoTotal += custoReducao2;
			break;
		case 3:
			custoTotal += custoReducao3;
			break;
		}
		atualizarCustos(tamanho);
		this.numeroReducoes++;

	}

	private void calculaCustosPorMM() {
		if (this.custoReducao1 != null) {
			this.custoPorMM1 = custoReducao1 / 1.0;
		}
		if (this.custoReducao2 != null) {
			this.custoPorMM2 = custoReducao2 / 2.0;
		}
		if (this.custoReducao3 != null) {
			this.custoPorMM3 = custoReducao3 / 3.0;
		}
		if (this.Largura == 4) {
			this.custoPorMM1 = null;
			this.custoPorMM2 = null;
			this.custoPorMM3 = null;
			this.custoReducao1 = null;
			this.custoReducao2 = null;
			this.custoReducao3 = null;
		}
	}

	private void atualizarCustos(int tamanho) {

		if (Largura - tamanho > 4) {
			Optional<Lamina> lamina = tabelaValores.stream().filter(n -> n.Largura == this.Largura - tamanho)
					.findFirst();
			Lamina resultado = lamina.get();
			this.custoReducao1 = resultado.custoReducao1;
			this.custoReducao2 = resultado.custoReducao2;
			this.custoReducao3 = resultado.custoReducao3;
		}
		this.Largura -= tamanho;
		calculaCustosPorMM();
	}

	public static Lamina encontraLaminaPorLargura(List<Lamina> listaLamina, int largura) {
		Optional<Lamina> lamina = listaLamina.stream().filter(p -> p.Largura == largura).findFirst();
		return lamina.get();
	}

	public Integer getCustoReducao1() {
		return custoReducao1;
	}

	public void setCustoReducao1(Integer custoReducao1) {
		this.custoReducao1 = custoReducao1;
	}

	public Integer getCustoReducao2() {
		return custoReducao2;
	}

	public void setCustoReducao2(Integer custoReducao2) {
		this.custoReducao2 = custoReducao2;
	}

	public Integer getCustoReducao3() {
		return custoReducao3;
	}

	public void setCustoReducao3(Integer custoReducao3) {
		this.custoReducao3 = custoReducao3;
	}

	public Double getCustoPorMM1() {
		return custoPorMM1;
	}

	public void setCustoPorMM1(Double custoPorMM1) {
		this.custoPorMM1 = custoPorMM1;
	}

	public Double getCustoPorMM2() {
		return custoPorMM2;
	}

	public void setCustoPorMM2(Double custoPorMM2) {
		this.custoPorMM2 = custoPorMM2;
	}

	public Double getCustoPorMM3() {
		return custoPorMM3;
	}

	public void setCustoPorMM3(Double custoPorMM3) {
		this.custoPorMM3 = custoPorMM3;
	}

	public int getLargura() {
		return Largura;
	}

	public int getCustoTotal() {
		return custoTotal;
	}

	public List<Lamina> getTabelaValores() {
		return tabelaValores;
	}

	public void setTabelaValores(List<Lamina> tabelaValores) {
		this.tabelaValores = tabelaValores;
	}

	public int getNumeroReducoes() {
		return numeroReducoes;
	}

	@Override
	public String toString() {
		return "Lamina [Largura=" + Largura + ", custoReducao1=" + custoReducao1 + ", custoReducao2=" + custoReducao2
				+ ", custoReducao3=" + custoReducao3 + ", custoPorMM1=" + custoPorMM1 + ", custoPorMM2=" + custoPorMM2
				+ ", custoPorMM3=" + custoPorMM3 + "]";
	}

}
