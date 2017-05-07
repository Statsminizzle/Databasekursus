package daointerfaces01917;

import java.util.List;

import dto01917.OperatoerDTO;

public interface IOperatoerDAO {
	OperatoerDTO getOperatoer(String oprCpr) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
