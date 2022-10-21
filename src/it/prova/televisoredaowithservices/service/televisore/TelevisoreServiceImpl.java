package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDao;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDao televisoreDao;

	public void setTelevisoreDao(TelevisoreDao televisoreDao) {
		this.televisoreDao = televisoreDao;
	}

	@Override
	public List<Televisore> listAll() throws Exception {
		List<Televisore> result = new ArrayList<Televisore>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);

			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public Televisore findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Televisore input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			televisoreDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = televisoreDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> televisoriIntervalloDate(Date primaData, Date secondaData) throws Exception {
		List<Televisore> listaTelevisori = new ArrayList<Televisore>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);

			listaTelevisori = televisoreDao.televisoriIntervalloDate(primaData, secondaData);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listaTelevisori;	}

	@Override
	public Televisore findTelevisoreConPiuPollici() throws Exception {
		// TODO Auto-generated method stub
				Televisore result = null;
				try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

					televisoreDao.setConnection(connection);
					result = televisoreDao.findTelevisoreConPiuPollici();

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				return result;
	}

	@Override
	public List<String> findMarcaTelevisoriProdottiNeiUltimiSeiMesi(Date seiMesi) throws Exception {
		List<String> result = new ArrayList<String>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.findMarcaTelevisoriProdottiNeiUltimiSeiMesi(seiMesi);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
