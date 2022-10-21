package it.prova.televisoredaowithservices.service;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDaoImpl;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreServiceImpl;

public class MyServiceFactory{
	
	public static TelevisoreService getTelevisoreService() {
		TelevisoreService televisoreService = new TelevisoreServiceImpl();
		televisoreService.setTelevisoreDao(new TelevisoreDaoImpl());
		return televisoreService;
	}
	

}