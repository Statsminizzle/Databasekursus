package test01917;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.ReceptKompDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.IReceptKompDAO;
import dto01917.ReceptKompDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestReceptKompDAO {

	private final static int TESTID = 2; //recept id
	private final static int TESTID2 = 3; //raavare id
	private static IReceptKompDAO dao;
	private static ReceptKompDTO dto;
	
	@BeforeClass
	public static void setup(){
		dao = new ReceptKompDAO();
		dto = new ReceptKompDTO(TESTID, TESTID2, 100, 5);
	}
	
	@Test
	public void createReceptKomp(){
		try { 
			dao.createReceptKomp(dto); 
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}	
	}
	
	@Test
	public void getReceptKomp(){
		try { 
			dto = dao.getReceptKomp(TESTID, TESTID2);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		assertNotNull(dto);
	}
	
	@Test
	public void updateReceptKomp(){
		dto.setTolerance(20);
		try { 
			dao.updateReceptKomp(dto);
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
		
		//check for equality
		try {
			assertEquals(dto.toString(), dao.getReceptKomp(TESTID, TESTID2).toString());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getReceptKompList(){
		try { 	
			assertNotNull(dao.getReceptKompList());
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@Test
	public void getReceptKompListFromID(){
		try {
			assertNotNull(dao.getReceptKompList(TESTID));
		} catch (DALException e) { 
			fail(e.getMessage()); 
		}
	}
	
	@AfterClass
	public static void cleanup(){
		try {
			Connector.doUpdate("DELETE FROM receptkomponent WHERE recept_id =  " + TESTID + " AND raavare_id = " + TESTID2);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
}