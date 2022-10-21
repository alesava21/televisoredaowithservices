package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDaoImpl extends AbstractMySQLDAO implements TelevisoreDao {

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Televisore> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore temp = new Televisore();
				temp.setId(rs.getLong("id"));
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setPollici(rs.getInt("pollici"));
				temp.setDataProduzione(rs.getDate("dataproduzione"));
				result.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1) {
			throw new Exception("Valore di input non ammesso.");

		}

		Televisore result = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?;")) {
			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					Televisore temp = new Televisore();
					temp.setId(rs.getLong("id"));
					temp.setMarca(rs.getString("marca"));
					temp.setModello(rs.getString("modello"));
					temp.setPollici(rs.getInt("pollici"));
					temp.setDataProduzione(rs.getDate("dataproduzione"));

				} else {
					result = null;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection
				.prepareStatement("update televisore set marca=?, modello=?, pollici=?, dataproduzione=? where id=?")) {

			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"insert into televisore (marca, modello, pollici, dataproduzione) values (?,?,?,?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public int delete(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("delete from televisore where id=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		List<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1;";
		if (input.getMarca() != null && input.getMarca().isBlank()) {
			query += " and marca like '" + input.getMarca() + "%' ";

		}
		if (input.getModello() != null && input.getModello().isBlank()) {
			query += "and modello like'" + input.getModello() + "%'";

		}

		if (input.getPollici() != 0) {
			query += "and pollici > " + input.getPollici() + "";

		}
		if (input.getDataProduzione() != null) {
			query += "and dataproduzione'" + new java.sql.Date(input.getDataProduzione().getTime()) + "'";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				while (rs.next()) {
					Televisore temp = new Televisore();
					temp.setId(rs.getLong("id"));
					temp.setMarca(rs.getString("marca"));
					temp.setModello(rs.getString("modello"));
					temp.setPollici(rs.getInt("pollici"));
					temp.setDataProduzione(rs.getDate("dataproduzione"));
					result.add(temp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> televisoriIntervalloDate(Date primaData, Date secondaData) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (primaData == null || secondaData == null)
			throw new Exception("Valore di input non ammesso.");
		
		List<Televisore> listaTelevisori = new ArrayList<Televisore>();
		Televisore temp = null;
		
		try (PreparedStatement ps= connection.prepareStatement("select * from televisore where dataproduzione > ? and dataproduzione < ?;")){
			ps.setDate(1, new java.sql.Date(primaData.getTime()));
			ps.setDate(2, new java.sql.Date(secondaData.getTime()));
			
			try (ResultSet rs= ps.executeQuery()){
				
				while (rs.next()) {
					temp = new Televisore();
					temp.setId(rs.getLong("id"));
					temp.setMarca(rs.getString("marca"));
					temp.setModello(rs.getString("modello"));
					temp.setPollici(rs.getInt("pollici"));
					temp.setDataProduzione(rs.getDate("dataproduzione"));
					listaTelevisori.add(temp);
				}
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listaTelevisori;
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

}
