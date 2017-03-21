package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IReceptDAO;
import dto01917.ReceptDTO;


public class ReceptDAO implements IReceptDAO {

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		try{
			PreparedStatement ps = Connector.prepare("SELECT recept_navn FROM recept WHERE recept_id = ?");
			ps.setInt(1, receptId);
			ResultSet rs = ps.executeQuery();
			if(!rs.first()){
				return null;
			} else {
				return new ReceptDTO(receptId, rs.getString("recept_navn"));
			}
		} catch (SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT recept_id, recept_navn FROM recept");
			while (rs.next()) {
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		try{
			PreparedStatement ps = Connector.prepare("INSERT INTO recept(recept_id, recept_navn) VALUES (?, ?);");
			ps.setInt(1, recept.getReceptId());
			ps.setString(2, recept.getReceptNavn());
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE recept SET recept_navn = ? WHERE recept_id = ?");
			ps.setString(1, recept.getReceptNavn());
			ps.setInt(2, recept.getReceptId());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
