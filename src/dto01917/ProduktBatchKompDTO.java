package dto01917;

public class ProduktBatchKompDTO 
{
	int pbId; 	  // produktbatchets id
	int rbId;        // i omraadet 1-99999999
	double tara;
	double netto;
	String oprCpr;					// operatoer-cpr

	
	public ProduktBatchKompDTO(int pbId, int rbId, String oprCpr, double tara, double netto)
	{
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.oprCpr = oprCpr;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public double getTara() { return tara; }
	public void setTara(double tara) { this.tara = tara; }
	public double getNetto() { return netto; }
	public void setNetto(double netto) { this.netto = netto; }
	public String getOprCpr() { return oprCpr; }
	public void setOprCpr(String oprCpr) { this.oprCpr = oprCpr; }
	public String toString() { 
		return pbId + "\t" + rbId +"\t" + tara +"\t" + netto + "\t" + oprCpr ; 
	}
}
