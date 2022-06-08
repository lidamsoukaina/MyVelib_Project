package core.classes;

import java.util.Objects;

import core.enumerations.BicycleType;

/**
 * Class for bicycle representation
 * @version 1.0
 */
public class Bicycle {
	// Attributes 
	private static int bicyclesCount = 0;
    private final int id = generateUniqueID();
    private BicycleType type;
    // Constructors
    /**
     * Instantiates a new Bicycle.
     *
     * @param type the bicycle type can of type BicycleType (enum: Mechanical,Electrical,...)
     */
    public Bicycle(BicycleType type) {
        this.type = type;
    }
    /**
     * Instantiates a new Bicycle with no defined type
     */
    public Bicycle(){}
	// Methods
    /**
     * Generates unique id for each bicycle
     */
    private synchronized int generateUniqueID(){
        return ++bicyclesCount;
    }
    // Setters
	public void setType(BicycleType type) {
		this.type = type;
	}
	// Getters
    public int getId() {
        return id;
    }
    public static int getBicyclesCount() {
		return bicyclesCount;
	}
    public BicycleType getType() {
		return type;
	}
    // Override Methods
    @Override
    public String toString() {
        return "Bicycle of id = " + id + " and of type " + type;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return id == bicycle.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}