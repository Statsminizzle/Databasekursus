package dto01917;

/**
 * Operatoer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class OperatoerDTO
{

	/** Operatoer cpr-nr 10 karakterer */
	String oprCpr;                     
	/** Operatoernavn (opr_navn) min. 2 max. 20 karakterer */
	String oprNavn;                
	/** Operatoer-initialer min. 2 max. 3 karakterer */
	String ini;                                
	/** Operatoer password min. 7 max. 8 karakterer */
	String password;            
	/** Operatoer rolle-id mellem 1-4 */
	int rolle;
	
	public OperatoerDTO(String oprCpr, String oprNavn, String ini, String password, int rolle)
	{
		this.oprCpr = oprCpr;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.password = password;
		this.rolle = rolle;
	}
	
    public OperatoerDTO(OperatoerDTO opr)
    {
    	this.oprCpr = opr.getOprCpr();
    	this.oprNavn = opr.getOprNavn();
    	this.ini = opr.getIni();
    	this.password = opr.getPassword();
    	this.rolle = opr.getRolle();
    }
    
   
	public String getOprCpr() { return oprCpr; }
	public void setOprId(String oprCpr) { this.oprCpr = oprCpr; }
	public String getOprNavn() { return oprNavn; }
	public void setOprNavn(String oprNavn) { this.oprNavn = oprNavn; }
	public String getIni() { return ini; }
	public void setIni(String ini) { this.ini = ini; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public int getRolle() { return rolle; }
	public void setRolle(int rolle) { this.rolle = rolle; }
	public String toString() { return oprCpr + "\t" + oprNavn + "\t" + ini + "\t" + password + "\t" + rolle; }
}
