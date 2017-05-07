package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;



public class ProduktBatchKompDAO implements IProduktBatchKompDAO {

	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbID, int rbID) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT opr_cpr, tara, netto FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?");
			ps.setInt(1, pbID);
			ps.setInt(2, rbID);
			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new ProduktBatchKompDTO(pbID, rbID, rs.getString("opr_cpr"), rs.getDouble("tara"), rs.getDouble("netto"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		try {
			PreparedStatement ps = Connector.prepare("SELECT rb_id, opr_cpr, tara, netto FROM produktbatchkomponent WHERE pb_id = ?");
			ps.setInt(1, pbId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProduktBatchKompDTO(pbId, rs.getInt("rb_id"), rs.getString("opr_cpr"), rs.getDouble("tara"), rs.getDouble("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
		
		
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT pb_id, rb_id, opr_cpr, tara, netto FROM produktbatchkomponent");
			while (rs.next()) {
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getString("opr_cpr"), rs.getDouble("tara"), rs.getDouble("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
		
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO produktbatchkomponent(pb_id, rb_id, opr_cpr, tara, netto) VALUES (?,?,?,?,?)");
			ps.setInt(1, produktbatchkomponent.getPbId());
			ps.setInt(2, produktbatchkomponent.getRbId());
			ps.setString(3, produktbatchkomponent.getOprCpr());
			ps.setDouble(4, produktbatchkomponent.getTara());
			ps.setDouble(5, produktbatchkomponent.getNetto());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE produktbatchkomponent SET tara = ?, netto = ?, opr_cpr = ? WHERE pb_id = ? AND rb_id = ?");
			ps.setDouble(1, produktbatchkomponent.getTara());
			ps.setDouble(2, produktbatchkomponent.getNetto());
			ps.setString(3, produktbatchkomponent.getOprCpr());
			ps.setInt(4, produktbatchkomponent.getPbId());
			ps.setInt(5, produktbatchkomponent.getRbId());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
