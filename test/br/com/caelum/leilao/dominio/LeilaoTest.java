package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;

public class LeilaoTest {	

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario steve = new Usuario("Steve Jobs");
		Leilao leilao = new LeilaoBuilder().para("Leilao I")
							.comLance(steve, 3000.00)
							.comLance(steve, 2000.00)
							.build();

		assertEquals(1, leilao.getLances().size());
		assertEquals(3000.00, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");
		Leilao leilao = new LeilaoBuilder().para("Leilao I")
								.comLance(steve, 3000.00)
								.comLance(bill, 2000.00)		
								.comLance(steve, 4000.00)
								.comLance(bill, 2000.00)		
								.comLance(steve, 3000.00)
								.comLance(bill, 6000.00)		
								.comLance(steve, 3000.00)
								.comLance(bill, 7000.00)		
								.comLance(steve, 3000.00)
								.comLance(bill, 8000.00)		
								.comLance(steve, 4000.00)
								.build();

		assertEquals(10, leilao.getLances().size());
		assertEquals(8000.00, leilao.getLances().get(9).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarLance() {
		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");
		Leilao leilao = new LeilaoBuilder().para("Leilao I")
							.comLance(steve, 3000.00)
							.comLance(bill, 2000.00)						
							.comLance(steve, 4000.00)
							.comLance(bill, 2000.00)						
							.comLance(steve, 3000.00)
							.comLance(bill, 6000.00)
							.build();
		
		leilao.dobraLance(steve);

		assertEquals(7, leilao.getLances().size());
		assertEquals(6000.00, leilao.getLances().get(6).getValor(), 0.00001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorNegativo() {
		Usuario steve = new Usuario("Steve Jobs");
		new LeilaoBuilder().para("Leilao I")
				.comLance(steve, -3000.00)
				.build();

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorIgualAZero() {
		Usuario steve = new Usuario("Steve Jobs");
		new LeilaoBuilder().para("Leilao I")
				.comLance(steve, 0.00)
				.build();
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
}
