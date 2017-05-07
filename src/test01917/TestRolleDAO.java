package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.RolleDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IRolleDAO;
import dto01917.RolleDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRolleDAO {

	private final static int TESTID = 6;
	private static IRolleDAO dao;
	private static RolleDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new RolleDAO();
		dto = new RolleDTO(TESTID, "TestRolle");
	}
	
	@Test
	public void createRolle(){
		try { 
			dao.createRolle(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getRolle(){
		try { 
			dto = dao.getRolle(TESTID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateRolle(){
		dto.setRolleNavn("NyRolleNavn");
		try { 
			dao.updateRolle(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getRolle(TESTID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getRolleList(){
		try { 
			assertNotNull(dao.getRolleList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM rolle WHERE rolle_id = " + TESTID);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}