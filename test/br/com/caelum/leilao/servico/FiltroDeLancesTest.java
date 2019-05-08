package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class FiltroDeLancesTest {
	
	private FiltroDeLances filtro;
	private Usuario joao;

	@Before
	public void setUp() {
		this.joao = new Usuario("Joao");
		this.filtro = new FiltroDeLances();
	}
	
	@Test
	public void deveSelecionarLancesEntre1000E3000() {		
		List<Lance> resultado = filtro.filtra(Arrays.asList(new Lance(joao, 2000), new Lance(joao, 1000),
				new Lance(joao, 3000), new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(2000, resultado.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveSelecionarLancesEntre500E700() {		
		List<Lance> resultado = filtro.filtra(
				Arrays.asList(new Lance(joao, 600), new Lance(joao, 500), new Lance(joao, 700), new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(600, resultado.get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveSelecionarLancesMaiores5000() {		
		List<Lance> resultado = filtro.filtra(
				Arrays.asList(new Lance(joao, 100), new Lance(joao, 7000), new Lance(joao, 6000), new Lance(joao, 200)));

		assertEquals(2, resultado.size());
		assertEquals(7000, resultado.get(0).getValor(), 0.00001);
		assertEquals(6000, resultado.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveSelecionarLancesListaVazia() {
		List<Lance> resultado = filtro.filtra(
				Arrays.asList(new Lance(joao, 100), new Lance(joao, 120), new Lance(joao, 800), new Lance(joao, 200)));

		assertEquals(0, resultado.size());
	}
}
