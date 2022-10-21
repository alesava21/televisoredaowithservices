package it.prova.televisoredaowithservices.service.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreService {
	public void setTelevisoreDao(TelevisoreDao televisoreDao);

	public List<Televisore> listAll() throws Exception;

	public Televisore findById(Long idInput) throws Exception;

	public int aggiorna(Televisore input) throws Exception;

	public int inserisciNuovo(Televisore input) throws Exception;

	public int rimuovi(Televisore input) throws Exception;

	public List<Televisore> findByExample(Televisore input) throws Exception;

	
	//##########################################################
	public List<Televisore> televisoriIntervalloDate(Date primaData, Date secondaData) throws Exception;

	public Televisore findTelevisoreConPiuPollici() throws Exception;

	public List<String> findMarcaTelevisoriProdottiNeiUltimiSeiMesi(Date seiMesi) throws Exception;


}
