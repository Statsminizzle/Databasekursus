package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.OperatoerDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IOperatoerDAO;
import dto01917.OperatoerDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOperatoerDAO {

	private final static String TESTCPR = "141516-2010";
	private static IOperatoerDAO dao;
	private static OperatoerDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new OperatoerDAO();
		dto = new OperatoerDTO(TESTCPR,"Don Juan","DJ", "iloveyou", 1);
	}
	
	@Test
	public void createOperatoer(){
		try { 
			dao.createOperatoer(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getOperatoer(){
		try { 
			dto = dao.getOperatoer(TESTCPR);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateOperatoer(){
		dto.setIni("DoJu");
		try { 
			dao.updateOperatoer(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getOperatoer(TESTCPR).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getOperatoerList(){
		try { 
			assertNotNull(dao.getOperatoerList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM operatoer WHERE opr_cpr = '" + TESTCPR + "'");
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}
