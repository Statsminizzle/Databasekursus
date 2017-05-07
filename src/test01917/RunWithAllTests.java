package test01917;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestOperatoerDAO.class, TestProduktBatchDAO.class, 
	TestProduktBatchKompDAO.class, TestReceptDAO.class,
	TestReceptKompDAO.class, TestRolleDAO.class,
	TestRaavareBatchDAO.class, TestRaavareDAO.class 
	})

public class RunWithAllTests {

}
