package daointerfaces01917;

import java.util.List;

import dto01917.RolleDTO;

public interface IRolleDAO {
	RolleDTO getRolle(int rolleId) throws DALException;
	List<RolleDTO> getRolleList() throws DALException;
	void createRolle(RolleDTO rolle) throws DALException;
	void updateRolle(RolleDTO rolle) throws DALException;
}


