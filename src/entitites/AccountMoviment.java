package entitites;

import java.util.Date;

public class AccountMoviment {

	private String type;
	private Date date;
	
	public AccountMoviment(String type, Date date) {
		this.type = type;
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}