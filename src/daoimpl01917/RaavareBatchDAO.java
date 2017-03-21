package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IRaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class RaavareBatchDAO implements IRaavareBatchDAO {

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT raavare_id, maengde FROM raavarebatch WHERE rb_id = ?");
			ps.setInt(1, rbId);
			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new RaavareBatchDTO(rbId, rs.getInt("raavare_id"), rs.getDouble("maengde"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		ArrayList<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT rb_id, raavare_id, maengde FROM raavarebatch");
			while (rs.next()) {
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		ArrayList<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		try {
			PreparedStatement ps = Connector.prepare("SELECT rb_id, maengde FROM raavarebatch WHERE raavare_id = ?");
			ps.setInt(1, raavareId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), raavareId, rs.getDouble("maengde")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO raavarebatch (rb_id, raavare_id, maengde) VALUES (?,?,?)");
			ps.setInt(1, raavarebatch.getRbId());
			ps.setInt(2, raavarebatch.getRaavareId());
			ps.setDouble(3, raavarebatch.getMaengde());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE raavarebatch SET raavare_id = ?, maengde = ? WHERE rb_id = ?");
			ps.setInt(1, raavarebatch.getRaavareId());
			ps.setDouble(2, raavarebatch.getMaengde());
			ps.setInt(3, raavarebatch.getRbId());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
