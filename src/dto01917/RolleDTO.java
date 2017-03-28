package dto01917;

public class RolleDTO {
	
	int rolleId;
	String rolleNavn;
	
	public RolleDTO (int rolleId, String rolleNavn){
		this.rolleId = rolleId;
		this.rolleNavn = rolleNavn;
	}
	
	
	
    public int getRolleId() { return rolleId; }
	public void setRolleId(int rolleId) { this.rolleId = rolleId; }
	public String getRolleNavn() { return rolleNavn; }
	public void setRolleNavn(String rolleNavn) { this.rolleNavn = rolleNavn; }
	

	public String toString() { 
		return rolleId + "\t" + rolleNavn;
	}

}
