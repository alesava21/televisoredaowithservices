package it.prova.televisoredaowithservices.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreService();

		try {

			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testInsertNuovoTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testUpdateTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testFindByExample(televisoreService);
			
			testTelevisoreIntervalloDate(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			
			testDeleteTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInsertNuovoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testInsertNuovoTelevisore inizio.............");
		Date dataInput = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021");
		Televisore newTelevisoreInstance = new Televisore("sony", "test", 42, dataInput);

		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1) {
			throw new RuntimeException("testInsertNuovoTelevisore FAILED ");
		}
		System.out.println(".......testInserimentoNuovoUser PASSED.............");
	}
	
	private static void testUpdateTelevisore(TelevisoreService televisoreService) throws Exception {
		Date dataInput = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021");
		List<Televisore> listaTelevisori = televisoreService.listAll();
		if (listaTelevisori.isEmpty()) {
			throw new RuntimeException("testUpdateTelevisore FAILED ");	
		}
		
		Televisore idPrimo= listaTelevisori.get(0);
		
		String marca="marca";
		String modello="modello";
		int pollici= 2;
		idPrimo.setMarca(marca);
		idPrimo.setModello(modello);
		idPrimo.setPollici(pollici);
		idPrimo.setDataProduzione(dataInput);
		
		televisoreService.aggiorna(idPrimo);
		
		for (Televisore televisoreItem : listaTelevisori) {
			System.out.println(televisoreItem.getMarca());
		}
		
		
		System.out.println(".......testUpdateUser PASSED.............");
	}

	private static void testDeleteTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testDeleteTelevisore inizio.............");
		List<Televisore> listaTelevisori = televisoreService.listAll();

		if (listaTelevisori.isEmpty()) {
			throw new RuntimeException("testRimozioneUser FAILED ");
		}
		int result = televisoreService.rimuovi(listaTelevisori.get(0));

		if (result < 1) {
			throw new RuntimeException("testDeleteImpiegato : FAILED, nessun record e' stato eliminato");

		}
		System.out.println(".......testDeleteTelevisore PASSED.............");
	}
	
	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testFindByExample inizio.............");

		List<Televisore> listaTelevisori= televisoreService.findByExample(new Televisore("d","b"));
		
		if (listaTelevisori.size() == 0) {
			throw new RuntimeException("testFindByExample FAILED ");
		}
		
		for (Televisore televisoreItem : listaTelevisori) {
			System.out.println(televisoreItem.getMarca());
		}
		System.out.println(".......testFindByExample PASSED.............");

	}
	
	private static void testTelevisoreIntervalloDate(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testTelevisoreIntervalloDate inizio.............");

		List<Televisore> listaTelevisori = televisoreService.listAll();
		
		if (listaTelevisori.isEmpty()) {
			throw new RuntimeException("testTelevisoreIntervalloDate FAILED ");
		}
		
		Date dataInput = new SimpleDateFormat("yyy-MM-dd").parse("1900-01-01");
		Date dataInput1 = new SimpleDateFormat("yyy-MM-dd").parse("2022-01-01");
		
		listaTelevisori = televisoreService.televisoriIntervalloDate(dataInput, dataInput1);

		if (listaTelevisori.isEmpty()) {
			throw new RuntimeException("testTelevisoreIntervalloDate FAILED ");

		}
		
		for (Televisore televisoreItem : listaTelevisori) {
			System.out.println(televisoreItem.getMarca() + " " + televisoreItem.getModello());
		}
		System.out.println(".......testTelevisoreIntervalloDate PASSED.............");

	}

}
