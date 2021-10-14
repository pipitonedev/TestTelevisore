package it.prova.test;

import java.util.Date;
import java.util.List;

import it.prova.model.Televisore;
import it.prova.service.MyServiceFactory;
import it.prova.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {

		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();

		try {

			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testGet(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testInserimentoNuovoTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testRimozioneTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testFindByExample(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testUpdateTelevisore(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testInserimentoNuovoTelevisore(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......TEST INSERT STARTING.............");
		Televisore newTelevisoreInstance = new Televisore("LG", "700G", new Date());
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoUser fallito ");

		System.out.println("inserito nuovo record: " + newTelevisoreInstance);
		System.out.println(".......TEST INSERT FINE.............");
	}

	public static void testGet(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......TEST GET INIZIO.............");

		Televisore televisoreDaPrendere = new Televisore("Sony", "KD329s", new Date());
		televisoreService.inserisciNuovo(televisoreDaPrendere);

		List<Televisore> listaTelevisori = televisoreService.listAll();
		Long idCercami = listaTelevisori.get(0).getId();

		if (televisoreService.findById(idCercami) == null) {
			throw new RuntimeException("testGetById fallito...");
		}

		for (Televisore item : listaTelevisori) {
			televisoreService.rimuovi(item);
		}
		System.out.println(".......TEST GET FINE.............");

	}

	private static void testRimozioneTelevisore(TelevisoreService televisoreService) throws Exception {

		System.out.println(".......TEST REMOVE STARTING............");
		List<Televisore> interoContenutoTabella = televisoreService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Nessun contenuto da rimuovere");

		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		Televisore rimuovi = televisoreService.findById(idDelPrimo);
		System.out.println("Stai rimuovendo: " + rimuovi);
		if (televisoreService.rimuovi(rimuovi) != 1)
			throw new RuntimeException("Il test di rimozione ha fallito! ");

		System.out.println("Rimosso il seguente articolo: " + rimuovi);
		System.out.println(".......TEST REMOVE FINE.............");
	}

	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {

		System.out.println(".......testFindByExample inizio.............");

		televisoreService.inserisciNuovo(new Televisore("Google", "gt890", new Date()));
		televisoreService.inserisciNuovo(new Televisore("Google", "vdgtr", new Date()));

		List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("Go"));
		if (risultatifindByExample.size() != 2)
			throw new RuntimeException("testFindByExample fallito ");

		for (Televisore televisoreItem : risultatifindByExample) {
			televisoreService.rimuovi(televisoreItem);
		}

		System.out.println(".......testFindByExample fine.............");
	}

	private static void testUpdateTelevisore(TelevisoreService televisoreService) throws Exception {

		System.out.println(".......TEST UPDATE STARTING.............");
		if (televisoreService.inserisciNuovo(new Televisore("Samsung", "Smartx", new Date())) != 1)
			throw new RuntimeException("TEST FALLITO! ");

		List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("Samsung", "Smartx"));
		if (risultatifindByExample.size() != 1)
			throw new RuntimeException("TEST UPDATE: testFindByExample fallito! ");

		Long dammiID = risultatifindByExample.get(0).getId();
		String nuovoModello = "tvsat";
		Televisore modifica = televisoreService.findById(dammiID);
		modifica.setModello(nuovoModello);
		System.out.println("Stai modificando il seguente elemento: " + modifica);
		if (televisoreService.aggiorna(modifica) != 1)
			throw new RuntimeException("TEST UPDATE FALLITO! ");

		System.out.println("aggiornato record: " + modifica);
		System.out.println(".......TEST UPDATE FINE.............");
	}

}
