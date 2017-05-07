package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.ReceptDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IReceptDAO;
import dto01917.ReceptDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestReceptDAO {

	private final static int TESTID = 99;
	private static IReceptDAO dao;
	private static ReceptDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new ReceptDAO();
		dto = new ReceptDTO(TESTID, "recepttest");
	}
	
	@Test
	public void createRecept(){
		try { 
			dao.createRecept(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getRecept(){
		try { 
			dto = dao.getRecept(TESTID);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateRaavare(){
		dto.setReceptNavn("recept99");
		try { 
			dao.updateRecept(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getRecept(TESTID).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getRaavareList(){
		try { 
			assertNotNull(dao.getReceptList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM recept WHERE recept_id = " + TESTID);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}