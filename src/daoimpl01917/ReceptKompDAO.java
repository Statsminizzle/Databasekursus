package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IReceptKompDAO;
import dto01917.ReceptKompDTO;

public class ReceptKompDAO implements IReceptKompDAO {

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws daointerfaces01917.DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT nom_netto, tolerance FROM receptkomponent WHERE raavare_id = ? AND recept_id = ?");
			ps.setInt(1, raavareId);
			ps.setInt(2, receptId);
			ResultSet rs = ps.executeQuery();

			if (!rs.first()) {
				return null;
			} else {
				return new ReceptKompDTO(receptId, raavareId, rs.getDouble("nom_netto"),
						rs.getDouble("tolerance"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws daointerfaces01917.DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		try {
			PreparedStatement ps = Connector.prepare("SELECT raavare_id, nom_netto, tolerance FROM receptkomponent WHERE recept_id = ?");
			ps.setInt(1, receptId);
			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				throw new DALException("No receipt with ID " + receptId + " could be found.");
			} else {
				do{
					System.out.println(rs.getInt("recept_id"));
					list.add(new ReceptKompDTO(receptId, rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs
							.getDouble("tolerance")));
				}while (rs.next());
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws daointerfaces01917.DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT recept_id, raavare_id, nom_netto, tolerance FROM receptkomponent");
			while (rs.next()) {
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs
						.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws daointerfaces01917.DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES (?,?,?,?)");
			ps.setInt(1, receptkomponent.getReceptId());
			ps.setInt(2, receptkomponent.getRaavareId());
			ps.setDouble(3, receptkomponent.getNomNetto());
			ps.setDouble(4, receptkomponent.getTolerance());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws daointerfaces01917.DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE receptkomponent SET nom_netto = ?, tolerance = ? WHERE recept_id = ? AND raavare_id = ?");
			ps.setDouble(1, receptkomponent.getNomNetto());
			ps.setDouble(2, receptkomponent.getTolerance());
			ps.setInt(3, receptkomponent.getReceptId());
			ps.setInt(4, receptkomponent.getRaavareId());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
