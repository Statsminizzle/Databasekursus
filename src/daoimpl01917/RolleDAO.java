package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IRolleDAO;
import dto01917.RolleDTO;

public class RolleDAO implements IRolleDAO {

	@Override
	public RolleDTO getRolle(int rolleId) throws DALException {
		
		 try {
		    	ResultSet rs = Connector.doQuery("SELECT * FROM rolle WHERE rolle_id = " + rolleId);
		    	if (!rs.first()) throw new DALException("Rollen " + rolleId + " findes ikke");
		    	return new RolleDTO (rs.getInt("rolle_id"), rs.getString("rolle_navn"));
		    }
		    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RolleDTO> getRolleList() throws DALException {
		
	List<RolleDTO> list = new ArrayList<RolleDTO>();
		
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM rolle");
			while (rs.next()) 
			{
				list.add(new RolleDTO(rs.getInt("rolle_id"), rs.getString("rolle_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
		
	}

	@Override
	public void createRolle(RolleDTO rolle) throws DALException {
		try {
			Connector.doUpdate(
				"INSERT INTO rolle(rolle_id, rolle_navn) VALUES " +
				"(" + rolle.getRolleId() + ", '" + rolle.getRolleNavn() + "')"
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error");
		}
		
	}

	@Override
	public void updateRolle(RolleDTO rolle) throws DALException {
		
		try {
			Connector.doUpdate(
					"UPDATE rolle SET  rolle_navn = '" + rolle.getRolleNavn()
					+ "' WHERE rolle_id = " +
					rolle.getRolleId()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error updating operators");
		}
		
	}

}
