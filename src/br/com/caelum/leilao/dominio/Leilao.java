package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {

		if (podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return lances.isEmpty() || (!usuario.equals(getUltimoLance().getUsuario()) && getTotalLances(usuario) < 5);
	}

	private long getTotalLances(Usuario usuario) {
		return lances.stream().filter(l -> usuario.equals(l.getUsuario())).count();
	}

	private Lance getUltimoLance() {
		return lances.get(lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		double valorUltimoLance = getValorUltimoLance(usuario);
		Lance novoLance = new Lance(usuario, valorUltimoLance * 2);
		lances.add(novoLance);
	}

	private double getValorUltimoLance(Usuario usuario) {
		List<Lance> lancesUsuario = lances.stream().filter(l -> usuario.equals(l.getUsuario()))
				.collect(Collectors.toList());
		return lancesUsuario.get(lancesUsuario.size() - 1).getValor();
	}

}
