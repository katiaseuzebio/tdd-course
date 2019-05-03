package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Leilao I");

		Usuario steve = new Usuario("Steve Jobs");
		// Usuario bill = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(steve, 2000.00));

		assertEquals(1, leilao.getLances().size());
		assertEquals(3000.00, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Leilao I");

		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 2000.00));
		
		leilao.propoe(new Lance(steve, 4000.00));
		leilao.propoe(new Lance(bill, 2000.00));
		
		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 6000.00));
		
		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 7000.00));
		
		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 8000.00));
		
		leilao.propoe(new Lance(steve, 4000.00));

		assertEquals(10, leilao.getLances().size());
		assertEquals(8000.00, leilao.getLances().get(9).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarLance() {
		Leilao leilao = new Leilao("Leilao I");

		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 2000.00));
		
		leilao.propoe(new Lance(steve, 4000.00));
		leilao.propoe(new Lance(bill, 2000.00));
		
		leilao.propoe(new Lance(steve, 3000.00));
		leilao.propoe(new Lance(bill, 6000.00));
		
		leilao.dobraLance(steve);

		assertEquals(7, leilao.getLances().size());
		assertEquals(6000.00, leilao.getLances().get(6).getValor(), 0.00001);
	}
	
	
	
}
