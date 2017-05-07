package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.RaavareBatchDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IRaavareBatchDAO;
import dto01917.RaavareBatchDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRaavareBatchDAO {

	private final static int TESTID = 99;
	private final static int TESTID2 = 2;
	private static IRaavareBatchDAO dao;
	private static RaavareBatchDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new RaavareBatchDAO();
		dto = new RaavareBatchDTO(TESTID, TESTID2, 200);
	}
	
	@Test
	public void createRaavareBatch(){
		try { 
			dao.createRaavareBatch(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getRaavareBatch(){
		try { 
			dto = dao.getRaavareBatch(TESTID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateRaavareBatch(){
		dto.setMaengde(500);
		try { 
			dao.updateRaavareBatch(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getRaavareBatch(TESTID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getRaavareBatchList(){
		try { 
			assertNotNull(dao.getRaavareBatchList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@Test
	public void getRaavareBatchListFromID(){
		try { 
			assertNotNull(dao.getRaavareBatchList(TESTID2));
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM raavarebatch WHERE rb_id = " + TESTID + " AND raavare_id = " + TESTID2);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}
