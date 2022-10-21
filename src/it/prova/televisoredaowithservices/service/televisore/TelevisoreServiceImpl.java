package it.prova.televisoredaowithservices.service.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	@Override
	public List<Televisore> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Televisore findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aggiorna(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rimuovi(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Televisore> televisoriIntervalloDate(Date primaData, Date secondaData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Televisore> findTelevisoreConPiuPollici(int pollici) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Televisore> findMarcaTelevisoriProdottiNeiUltimiSeiMesi(Date dataSeiMesi) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTelevisoreDao(TelevisoreDao televisoreDao) {
		// TODO Auto-generated method stub
		
	}


}
