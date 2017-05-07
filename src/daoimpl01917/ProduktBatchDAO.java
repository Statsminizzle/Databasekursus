package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daointerfaces01917.IProduktBatchDAO;
import dto01917.ProduktBatchDTO;
import daointerfaces01917.DALException;
import connector01917.Connector;

public class ProduktBatchDAO implements IProduktBatchDAO {
	
	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT recept_id, status FROM produktbatch WHERE pb_id = ?");
			ps.setInt(1, pbId);
			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new ProduktBatchDTO(pbId, rs.getInt("status"), rs.getInt("recept_id"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT pb_id, recept_id, status FROM produktbatch");
			while (rs.next()) {
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO produktbatch (pb_id, recept_id, status) VALUES (?,?,?)");
			ps.setInt(1, produktbatch.getPbId());
			ps.setInt(2, produktbatch.getReceptId());
			ps.setInt(3, produktbatch.getStatus());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE produktbatch SET status = ?, recept_id = ? WHERE pb_id = ?");
			ps.setInt(1, produktbatch.getStatus());
			ps.setInt(2, produktbatch.getReceptId());
			ps.setInt(3, produktbatch.getPbId());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
