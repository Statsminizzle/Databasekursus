package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.ProduktBatchKompDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProduktBatchKompDAO {

	private final static int TESTID = 1;
	private final static int TESTRBID = 5;
	private static IProduktBatchKompDAO dao;
	private static ProduktBatchKompDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new ProduktBatchKompDAO();
		dto = new ProduktBatchKompDTO(TESTID, TESTRBID, "141516-2010", 200, 100);
	}
	
	@Test
	public void createProduktBatchKomp(){
		try { 
			dao.createProduktBatchKomp(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getProduktBatchKomp(){
		try { 
			dto = dao.getProduktBatchKomp(TESTID, TESTRBID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateProduktBatchKomp(){
		dto.setNetto(10.00);
		try { 
			dao.updateProduktBatchKomp(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getProduktBatchKomp(TESTID, TESTRBID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getProduktBatchKompList(){
		try { 
			assertNotNull(dao.getProduktBatchKompList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@Test
	public void getProduktBatchKompListFromID(){
		try { 
			assertNotNull(dao.getProduktBatchKompList(TESTID));
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM produktbatchkomponent WHERE pb_id = " + TESTID + " AND rb_id = " + TESTRBID);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}
