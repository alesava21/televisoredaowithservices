package it.prova.televisoredaowithservices.dao.televisore;

import java.util.Date;
import java.util.List;
import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDao extends IBaseDAO<Televisore> {
	public List<Televisore> televisoriIntervalloDate(Date primaData, Date secondaData) throws Exception;

	public List<Televisore> findTelevisoreConPiuPollici(int pollici) throws Exception;

	public List<Televisore> findMarcaTelevisoriProdottiNeiUltimiSeiMesi(Date dataSeiMesi) throws Exception;

}
