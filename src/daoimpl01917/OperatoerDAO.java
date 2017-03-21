package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.IOperatoerDAO;
import dto01917.OperatoerDTO;

public class OperatoerDAO implements IOperatoerDAO {
	public OperatoerDTO getOperatoer(int oprCpr) throws DALException {
	    try {
	    	ResultSet rs = Connector.doQuery("SELECT * FROM operatoer WHERE opr_cpr = " + oprCpr);
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprCpr + " findes ikke");
	    	return new OperatoerDTO (rs.getString("opr_cpr"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			try {
				Connector.doUpdate(
					"INSERT INTO operatoer(opr_cpr, opr_navn, ini, password) VALUES " +
					"(" + opr.getOprCpr() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + "', '" + opr.getPassword() + "')"
				);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Error");
			}
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		try {
			Connector.doUpdate(
					"UPDATE operatoer SET  opr_navn = '" + opr.getOprNavn() + "', ini =  '" + opr.getIni() + 
					"', password = '" + opr.getPassword() + "' WHERE opr_cpr = " +
					opr.getOprCpr()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Error updating operators");
		}
	}
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		
		try
		{
			ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getString("opr_cpr"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
