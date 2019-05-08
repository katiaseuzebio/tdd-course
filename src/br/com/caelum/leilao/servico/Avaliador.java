package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaLances = 0.0;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		
		if(leilao.getQuantidadeLances() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances");
		}
		
		double totalLances = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}

			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
			totalLances += lance.getValor();
		}
		this.mediaLances = totalLances / leilao.getQuantidadeLances();
		pegaOsMaioresNo(leilao);
	}

	private void pegaOsMaioresNo(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, Comparator.comparing(Lance::getValor).reversed());
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

	public double getMediaLance() {
		return mediaLances;
	}

	public List<Lance> getMaiores() {
		return maiores;
	}

}