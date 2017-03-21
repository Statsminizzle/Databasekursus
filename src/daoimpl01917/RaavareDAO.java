package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.IRaavareDAO;
import dto01917.RaavareDTO;

public class RaavareDAO implements IRaavareDAO {

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT raavare_navn, leverandoer FROM raavare WHERE raavare_id= ?");
			ps.setInt(1, raavareId);
			ResultSet rs = ps.executeQuery();
			if (!rs.first()){
				return null;
			} else {
				return new RaavareDTO(raavareId, rs.getString("raavare_navn"), rs.getString("leverandoer"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		try {
			ResultSet rs = Connector.doQuery("SELECT raavare_id, raavare_navn, leverandoer FROM raavare");
			while (rs.next()) {
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO raavare (raavare_id, raavare_navn, leverandoer) VALUES (?,?,?);");
			ps.setInt(1, raavare.getRaavareId());
			ps.setString(2, raavare.getRaavareNavn());
			ps.setString(3, raavare.getLeverandoer());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		try{
			PreparedStatement ps = Connector.prepare("UPDATE raavare SET raavare_navn = ?, leverandoer = ? WHERE raavare_id = ?");
			ps.setString(1, raavare.getRaavareNavn());
			ps.setString(2, raavare.getLeverandoer());
			ps.setInt(3, raavare.getRaavareId());
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

}
