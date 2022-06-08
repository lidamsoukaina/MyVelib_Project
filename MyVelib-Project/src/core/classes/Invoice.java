package core.classes;

/**
 * Class to represent the bill of a ride
 * @version 1.0
 */
public class Invoice {
	// Attributes
	private int charge;
	private int usedTimeCredit;
	// Constructor
	public Invoice(int charge, int usedTimeCredit) {
		this.charge = charge;
		this.usedTimeCredit = usedTimeCredit;
	
	}
	// Getters and Setters
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getUsedTimeCredit() {
		return usedTimeCredit;
	}
	public void setUsedTimeCredit(int usedTimeCredit) {
		this.usedTimeCredit = usedTimeCredit;
	}
}
