package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.RaavareDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IRaavareDAO;
import dto01917.RaavareDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRaavareDAO {

	private final static int TESTID = 99;
	private static IRaavareDAO dao;
	private static RaavareDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new RaavareDAO();
		dto = new RaavareDTO(TESTID, "TestRaavare", "TestFirma");
	}
	
	@Test
	public void createRaavare(){
		try { 
			dao.createRaavare(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getRaavare(){
		try { 
			dto = dao.getRaavare(TESTID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateRaavare(){
		dto.setLeverandoer("NytFirma");
		try { 
			dao.updateRaavare(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getRaavare(TESTID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getRaavareList(){
		try { 
			assertNotNull(dao.getRaavareList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM raavare WHERE raavare_id = " + TESTID);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}