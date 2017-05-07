package test01917;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/*import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
*/
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import connector01917.Connector;
import daoimpl01917.OperatoerDAO;
import daoimpl01917.ProduktBatchDAO;
import daoimpl01917.ProduktBatchKompDAO;
import daoimpl01917.RaavareBatchDAO;
import daoimpl01917.RaavareDAO;
import daoimpl01917.ReceptDAO;
import daoimpl01917.ReceptKompDAO;
import daoimpl01917.RolleDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;
import dto01917.RolleDTO;

public class Main {
	public static void main(String[] args) throws SQLException {
		Connector connector = new Connector();
		Connection connection = connector.getConnection();
		Statement st = connection.createStatement();
		//Connector.doUpdate("DELETE FROM rolle WHERE rolle_id = " + 6);
		//in parameter sp ==   {call my_procedure(?)};
        //in+out parameter sp = {? = call my_procedure(?)};
        //Statement test = connection.createStatement();
        //String storedprocedure = "{call test(?)}";
        
        //test.execute(storedprocedure);
		//ResultSet result = test.getResultSet();	
		
        //CallableStatement cs = connection.prepareCall(storedprocedure); 
        //cs.setInt(1, 1);
        //ResultSet results = cs.executeQuery();
        
        
        
		
	}
	
	public void testOperatoer(){
		OperatoerDAO opr = new OperatoerDAO();
		
		System.out.println("Indsaettelse af ny operatoer med oprcpr =  141516-2010");
		OperatoerDTO oprDTO = new OperatoerDTO("141516-2010","Don Juan","DJ", "iloveyou", 1);
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Operatoer med cpr 141516-2010:");
		try { System.out.println(opr.getOperatoer("141516-2010")); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af initialer for operatoer med cpr 141516-2010");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer med cpr 141516-2010:");
		try { System.out.println(opr.getOperatoer("141516-2010")); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM operatoer WHERE opr_cpr = '141516-2010'")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testProduktBatch(){
		ProduktBatchDAO dao = new ProduktBatchDAO();
		
		System.out.println("Indsaettelse af ny produktbatch med pbid = 99");
		ProduktBatchDTO dto = new ProduktBatchDTO(99, 1, 4);
		try { dao.createProduktBatch(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Produktbatch med pbid 99:");
		try { System.out.println(dao.getProduktBatch(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af status for produktbatch med pbid 99");
		dto.setStatus(2);
		try { dao.updateProduktBatch(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch med pbid 99:");
		try { System.out.println(dao.getProduktBatch(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatches:");
		try { System.out.println(dao.getProduktBatchList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM produktbatch WHERE pb_id = 99")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testProduktBatchKomp(){
		ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
		
		System.out.println("Indsaettelse af ny produktbatchkomp med pbid = 99 og rbid 2");
		ProduktBatchKompDTO dto = new ProduktBatchKompDTO(99, 2, "141516-2010", 200, 100);
		try { dao.createProduktBatchKomp(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Produktbatchkomp med pbid 99 og rbid 2:");
		try { System.out.println(dao.getProduktBatchKomp(99, 2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af netto for produktbatchkomp med pbid 99 og rbid 2");
		dto.setNetto(400);
		try { dao.updateProduktBatchKomp(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch med pbid 99 og rbid 2:");
		try { System.out.println(dao.getProduktBatchKomp(99, 2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatches:");
		try { System.out.println(dao.getProduktBatchKompList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatchkomp for produktbatch med id 1:");
		try { System.out.println(dao.getProduktBatchKompList(1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM produktbatchkomponent WHERE pb_id = 99 AND rb_id = 2")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testRecept(){
		ReceptDAO dao = new ReceptDAO();
		
		System.out.println("Indsaettelse af ny recept med receptid 99");
		ReceptDTO dto = new ReceptDTO(99, "recepttest");
		try { dao.createRecept(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Recept med id 99:");
		try { System.out.println(dao.getRecept(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af navn for recept med id 99");
		dto.setReceptNavn("recept99");
		try { dao.updateRecept(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Recept med id 99:");
		try { System.out.println(dao.getRecept(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle recepter:");
		try { System.out.println(dao.getReceptList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM recept WHERE recept_id = 99")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testReceptKomp(){
		ReceptKompDAO dao = new ReceptKompDAO();
		
		System.out.println("Indsaettelse af ny receptkomp med receptid 99 og raavareid 2");
		ReceptKompDTO dto = new ReceptKompDTO(99, 2, 100, 5);
		try { dao.createReceptKomp(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("ReceptKomp med receptid 99 og raavareid 2:");
		try { System.out.println(dao.getReceptKomp(99, 2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af tolerance for receptkomp");
		dto.setTolerance(20);
		try { dao.updateReceptKomp(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("ReceptKomp med ny tolerance:");
		try { System.out.println(dao.getReceptKomp(99, 2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle ReceptKomp:");
		try { System.out.println(dao.getReceptKompList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle ReceptKomp for recept med id 1:");
		try { System.out.println(dao.getReceptKompList(1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM receptkomponent WHERE recept_id = 99 AND raavare_id = 2")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testRolle(){
		RolleDAO dao = new RolleDAO();
		
		System.out.println("Indsaettelse af ny rolle med id 6");
		RolleDTO dto = new RolleDTO(6, "TestRolle");
		try { dao.createRolle(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Rolle med id 6:");
		try { System.out.println(dao.getRolle(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af navn for rolle med id 6");
		dto.setRolleNavn("NyRolleNavn");
		try { dao.updateRolle(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Rolle med nyt navn:");
		try { System.out.println(dao.getRolle(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle Roller:");
		try { System.out.println(dao.getRolleList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM rolle WHERE rolle_id = 6")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void testRaavareBatch(){
		RaavareBatchDAO dao = new RaavareBatchDAO();
		
		System.out.println("Indsaettelse af ny raavarebatch med id 99");
		RaavareBatchDTO dto = new RaavareBatchDTO(99, 2, 200);
		try { dao.createRaavareBatch(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("RaavareBatch med id 99:");
		try { System.out.println(dao.getRaavareBatch(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af maengde for RaavareBatch med id 99");
		dto.setMaengde(500);
		try { dao.updateRaavareBatch(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("RaavareBatch med ny maengde:");
		try { System.out.println(dao.getRaavareBatch(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle RaavareBatch:");
		try { System.out.println(dao.getRaavareBatchList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle RaavareBatches for raavare med id 1:");
		try { System.out.println(dao.getRaavareBatchList(1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM raavarebatch WHERE rb_id = 99")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	

	public void testRaavare(){
		RaavareDAO dao = new RaavareDAO();
		
		System.out.println("Indsaettelse af ny raavare med id 99");
		RaavareDTO dto = new RaavareDTO(99, "TestRaavare", "TestFirma");
		try { dao.createRaavare(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Raavare med id 99:");
		try { System.out.println(dao.getRaavare(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af leverandør for raavare med id 99");
		dto.setLeverandoer("NytFirma");
		try { dao.updateRaavare(dto); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavare med ny leverandør:");
		try { System.out.println(dao.getRaavare(99)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle Raavarer:");
		try { System.out.println(dao.getRaavareList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Sletter test-dataen");
		try { System.out.println(Connector.doQuery("DELETE FROM raavare WHERE raavare_id = 99")); }
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
}
