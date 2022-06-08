package core.classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import core.enumerations.BicycleType;
import core.enumerations.SlotStatus;
import core.enumerations.StationStatus;
import core.enumerations.StationType;

/**
 * Class for station's representation
 * @version 1.0
 */
public class Station {
	// Attributes
    private static int stationsCount = 0; // Static number to use it to identify each instance station
    private final int id = generateUniqueID();
    private Point coordinate;
    private StationStatus status;  
    private StationType type;
    private List<Slot> parkingSlots = new ArrayList<>();
    private int totalDrops;
    private int totalRents;
    // Constructors
    /**
     * Construct a station in a defined location and specific
     * status and type
     * @param coordinate    the coordinates of the station
     * @param status  		the status of the station : on service or offline ...
     * @param type		    the type of station : plus or standard
     */
    public Station(Point coordinate, StationStatus status, StationType type,List<Slot> parkingSlots) {
        this.coordinate = coordinate;
        this.status = status;
        this.type = type;
        this.parkingSlots = parkingSlots;
    }
    /**
     * Construct a station in a certain coordinate
     * @param coordinate    the coordinates of the station
     */
    public Station(Point coordinate) {
        this.coordinate = coordinate;
    }
    /**
     * Construct a station in a defined location and specific
     * status and type with a list of parking slots
     * @param coordinate    the coordinates of the station
     * @param status  		the status of the station : on service or offline ...
     * @param type		    the type of station : plus or standard
     */
    public Station(Point coordinate, StationStatus status, StationType type) {
        this.coordinate = coordinate;
        this.status = status;
        this.type = type;
    }
    // Methods
    /**
     * Generates unique id for each station
     */
    private int generateUniqueID() {
        return ++stationsCount;
    }
    /**
     * Checks if a station has a status = StationStatus.OnService
     */
    public boolean isOnService() {
        return this.status == StationStatus.OnService;
    }
    /**
     * Adds a parking slot to a station
     * @param slot the parking slot to be added to the station
     */
    public void addSlot(Slot slot){
    	this.parkingSlots.add(slot);
    }
    /**
     * Adds n-slots free slots
     * @param nslots the count of slots to be added
     */
    public void addFreeSlots(int nslots){
    	for (int i = 0; i < nslots; i++) {
    		Slot slot = new Slot();
            if(this.parkingSlots == null) {
            	this.parkingSlots = new ArrayList<Slot>();
            }
    		this.parkingSlots.add(slot);
    	}
    }
    /**
     * Checks if the station has a bicycle of the given
     * type from supported types in enumeration BicycleType. 
     * @param bicycleType  the type of bicycle
     * @return true if the station has a bicycle of the given type, false otherwise
     */
    public boolean hasBicycleOfType(BicycleType bicycleType) {
        for (Slot slot : parkingSlots) {
            if (slot.getBicycle().getType() == bicycleType) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the station has a free slot.
     */
    public boolean hasFreeSlot() {
        for (Slot slot : parkingSlots) {
            if (slot.getStatus() == SlotStatus.Free) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the total count of parking slots within the station.
     * @return the total number of slots
     */
    public int getParkingSlotsCount(){
        return this.parkingSlots.size();
    }
    /**
     * Gets the count of free parking slots within the station.
     * @return the number of free slots
     */
    public int getFreeSlotsCount(){
        int freeSlotsCount=0;
        for (Slot slot : parkingSlots) {
            if (slot.getStatus() == SlotStatus.Free){
            	freeSlotsCount++;
            }
        }
        return freeSlotsCount;
    }
    /**
     * Gets the count of bicycles with the given type in the station.
     * @param typeOfBicycle the type of bicycle
     * @return the count of bicycle with the given type in the station
     */
    public int getBicyclesCount(BicycleType typeOfBicycle){
        int countOfBicycles=0;
        for (Slot slot : parkingSlots) {
            if (slot.getBicycle().getType() == typeOfBicycle && slot.getStatus() == SlotStatus.Occupied){
            	countOfBicycles++;
            }
        }
        return countOfBicycles;
    }
    /**
     * Gets a slot with the demanded type of bicycle.
     * @param typeOfBicycle the type of bicycle
     * @return a slot with the demanded type of bicycle
     */
    public Slot getSlotWithBicycleOfType(BicycleType typeOfBicycle){
    	for (Slot slot : parkingSlots) {
            if (slot.getStatus() == SlotStatus.Occupied && slot.getBicycle().getType()== typeOfBicycle){
            	return slot;
            }
        }
        return null;
    }
    /**
     * Gets a free slot.
     * @return a free parking slot within the station
     */
    public Slot getSlotOfStatus(SlotStatus status ){
    	for (Slot slot : parkingSlots) {
            if (slot.getStatus() == status){
            	return slot;
            }
        }
        return null;
    }
    /**
     * Gets the associated time credit bonus to this station 
     * taking into account its type
     * @return a the time credit bonus
     */
    public int getTimeCreditBonus(){
    	if (this.getType() == StationType.Plus){
    		return 5;
    	}
    	return 0;
    }
    // Setters
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
	public void setStationStatus(StationStatus status) {
		this.status = status;
	}
    public void setSlots(List<Slot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }
    public void setTotalDrops(int totalDrops) {
        this.totalDrops = totalDrops;
    }
    public void setTotalRents(int totalRents) {
        this.totalRents = totalRents;
    }
	public void setType(StationType type) {
		this.type = type;
	}
	// Getters
    public int getId() {
        return this.id;
    }
    public StationStatus getStationStatus() {
		return status;
	}
    public Point getCoordinate() {
        return coordinate;
    }
    public List<Slot> getSlots() {
        return parkingSlots;
    }
    public static int getStationsCount() {
        return stationsCount;
    }
    public int getTotalDrops() {
        return totalDrops;
    }
    public int getTotalRents() {
        return totalRents;
    }
	public StationType getType() {
		return type;
	}
    // Override Methods
    @Override
    public String toString() {
        return "Station of " +
                "id = " + id +
                ", coordinate = " + coordinate +
                ", status = " + status +
                ", parkingSlots = " + parkingSlots +
                ", totalDrops = " + totalDrops +
                ", totalRents = " + totalRents;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id &&
                status == station.status &&
                totalDrops == station.totalDrops &&
                totalRents == station.totalRents &&
                Objects.equals(coordinate, station.coordinate) &&
                Objects.equals(parkingSlots, station.parkingSlots);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, coordinate, status, parkingSlots, totalDrops, totalRents);
    }
}
