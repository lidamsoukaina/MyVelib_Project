package core.classes;

import java.util.Objects;

import core.enumerations.BicycleType;
import core.enumerations.SlotStatus;

/**
 * Class for parking slot representation
 * @version 1.0
 */
public class Slot {
	// Attributes
    private static int slotsCount = 0;
    private final int id = generateUniqueID(); // We decided to generate a unique id for all slots regardless their station
	private SlotStatus status;
    private Bicycle bicycle;
    // Constructors
    /**
     * Instantiates a new slot with no affected bicycle.
     * The parking slot is free by default
     */
    public Slot() {
        this.status = SlotStatus.Free;
        this.bicycle = null;
    }
    /**
     * Instantiates a new Parking slot with a status
     *
     * @param slotStatus        the slot status
     * @param bicycle           the bicycle if one is parked, null otherwise.
     * @throws Exception throw error in case of inconsistencies (i.e no occupied slot with no bicycle) 
     */
    public Slot(SlotStatus slotStatus, Bicycle bicycle) throws Exception {
    	BicycleType type = bicycle.getType();
        if (slotStatus == SlotStatus.Occupied && Objects.nonNull(type)){
            this.bicycle = bicycle;
            this.status = SlotStatus.Occupied;
        }
        else if (slotStatus == SlotStatus.Occupied && Objects.isNull(type)){
        	throw new Exception("Error: Can't set slot to occupied by an undefined bicycle");
        }
        else if (slotStatus == SlotStatus.Free && Objects.nonNull(type)){
            throw new Exception("Error: Can't creat free slot with a bicycle");
        }
        else {
            this.bicycle = bicycle;
            this.status = slotStatus;
        }
    }
    /**
     * Instantiates a slot occupied by a bicycle
     * @param bicycle the bicycle to affect to the parking slot
     */
    public Slot(Bicycle bicycle) {
        this.status = SlotStatus.Occupied;
        this.bicycle = bicycle;
    }
    /**
     * Instantiates a new slot with a defined status
     * @throws Exception throw error in case of trying to create an occupied slot
     * without providing the bicycle information
     */
    public Slot(SlotStatus status) throws Exception {
    	if (status == SlotStatus.Occupied ) {
    		throw new Exception("Error: No provided bicycle to add to this slot");
    	}
    	else {
	        this.status = status;
	        this.bicycle = null;
        }
    }
    // Methods
    /**
     * Generates unique id for each bicycle
     */
    private int generateUniqueID() {
        return ++slotsCount;
    }
    /**
     * Add a bicycle to the slot and change its status to occupied
     * @param bicycle the bicycle to put in the slot
     * @throws Exception if the slot is already taken
     */
    public void addBicycle(Bicycle bicycle) throws Exception {
    	if(status == SlotStatus.Occupied) {
    		throw new Exception("Error: Error: Can't add a bicycle to an occupied slot");
    	}
    	else if(bicycle == null) {
    		throw new Exception("Error: No provided bicycle to add to this slot");
    	}
    	else {
	        this.bicycle = bicycle;
	        this.status = SlotStatus.Occupied;
	        }
    }
    // Setters
    public void setStatus(SlotStatus status) {
        this.status = status;
    }
    public void setStatus(SlotStatus status, Bicycle bicycle) {
        this.status = status;
        this.bicycle = bicycle;
    }
    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }
    // Getters
    public Bicycle getBicycle() {
        return bicycle;
    }
    public SlotStatus getStatus() {
        return this.status;
    }
    public int getId() {
        return this.id;
    }
    public static int getSlotsCount() {
        return slotsCount;
    }
    // Override Methods
    @Override
    public String toString() {
        return "ParkingSlot of id = " + id + " and state = " + status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot that = (Slot) o;
        return id == that.id &&
                status == that.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
