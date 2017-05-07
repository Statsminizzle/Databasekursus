package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.ProduktBatchDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IProduktBatchDAO;
import dto01917.ProduktBatchDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProduktBatchDAO {

	private final static int TESTID = 99;
	private static IProduktBatchDAO dao;
	private static ProduktBatchDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new ProduktBatchDAO();
		dto = new ProduktBatchDTO(TESTID, 1, 4);
	}
	
	@Test
	public void createProduktBatch(){
		try { 
			dao.createProduktBatch(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getProduktBatch(){
		try { 
			dto = dao.getProduktBatch(TESTID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateProduktBatch(){
		dto.setStatus(3);
		dto.setReceptId(2);
		try { 
			dao.updateProduktBatch(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getProduktBatch(TESTID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getProduktBatchList(){
		try { 
			assertNotNull(dao.getProduktBatchList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM produktbatch WHERE pb_id = " + TESTID);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}
