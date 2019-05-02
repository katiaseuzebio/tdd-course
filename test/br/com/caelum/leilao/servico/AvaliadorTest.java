package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario: 1 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void testaMediaLances() {
		// cenario: 2 media dos lances
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double mediaLances = 316.6666666666667;

		assertEquals(mediaLances, leiloeiro.getMediaLance(), 0.0001);
	}

	@Test
	public void testaLeilaoComUmLance() {
		// cenario: 3 leilao com apenas um lance
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		assertEquals(250.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void testaTresMaiores() {
		// cenario: 4 Um leilão com 5 lances, deve encontrar os três maiores
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(jose, 500.0));
		leilao.propoe(new Lance(jose, 100.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getMaiores();
		assertEquals(3, maiores.size());
		assertEquals(700, maiores.get(0).getValor(), 0.0001);
		assertEquals(500, maiores.get(1).getValor(), 0.0001);
		assertEquals(400, maiores.get(2).getValor(), 0.0001);
	}

	@Test
	public void testaTresMaioresListaMenor() {
		// cenario: 5 Um leilão com 2 lances, deve devolver apenas os dois lances que
		// encontrou
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getMaiores();
		assertEquals(2, maiores.size());
		assertEquals(300, maiores.get(0).getValor(), 0.0001);
		assertEquals(250, maiores.get(1).getValor(), 0.0001);
	}

	@Test
	public void testaTresMaioresListaVazia() {
		// cenario: 6 Um leilão sem nenhum lance, devolve lista vazia
		Leilao leilao = new Leilao("Playstation 3 Novo");

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		List<Lance> maiores = leiloeiro.getMaiores();
		assertEquals(0, maiores.size());
	}
}
