package core.classes;

/**
 * Class representing rode planning proposition
 * represented by a start and an end stations
 * @version 1.0
 */
public class RideProposition {
	// Attributes
	private Station startStation;
	private Station endStation;
	// Constructors
    /**
     * Class constructor
     * @param startStation the proposed start station
     * @param endStation   the proposed end station
     */
	public RideProposition(Station startStation, Station endStation) {
		this.startStation = startStation;
		this.endStation = endStation;
	}
    /**
     * Default constructor
     */
	public RideProposition() {
	}
	// Getters and Setters
	public Station getStartStation() {
		return startStation;
	}
	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	
}
