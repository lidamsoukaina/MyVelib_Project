package core.classes;

import java.awt.Point;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


import core.enumerations.BicycleType;
import core.enumerations.RegistrationCardType;
import core.enumerations.SlotStatus;
import core.interfaces.RegistrationCardVisitor;
import core.interfaces.RidePlanningAlgorithm;

/**
 * Class for user representation
 * @version 1.0
 */
public class User {
	// Attributes
    private static int usersCount;
    private final int id = generateUniqueID();
    private String name;
    private Point coordinate;
    private String creditCardNumber;
    private RegistrationCard registrationCard;
    private double totalRides;
    private double totalCharges;
    private double totalTimeCredit;
    private double totalRentTime;
    private Bicycle rentedBicycle;
    private LocalDateTime rentDateTime;
    // Methods
    /**
     * Generates unique id for each user
     */
    private int generateUniqueID() {
        return ++usersCount;
    }
    // Constructors
	/**
     * Create a new User with a registration card
     * @param name             the name
     * @param coordinate       the user's coordinate
     * @param creditCardNumber the credit card number
     * @param registrationCard the registration card hold by the user
     */
    public User(String name, Point coordinate, String creditCardNumber, RegistrationCard registrationCard) {
		super();
		this.name = name;
		this.coordinate = coordinate;
		this.creditCardNumber = creditCardNumber;
		if(registrationCard == null) {
	        this.registrationCard = new NoRegistrationCard();
	        this.registrationCard.setHolder(this);
		}
		else {
			this.registrationCard = registrationCard;
	        this.registrationCard.setHolder(this);
		}
	} 
	/**
     * Create a new User with no registration card.
     * @param name             the name
     * @param coordinate       the user's coordinate
     * @param creditCardNumber the credit card number
     * @throws Exception 	   the exception
     */
    public User(String name, Point coordinate, String creditCardNumber) throws Exception {
        this.name = name;
		this.coordinate = coordinate;
        this.creditCardNumber = creditCardNumber; // Bonus: we can add a credit card validity checker
        this.registrationCard = new NoRegistrationCard();
    }    
	/**
     * Create a new User with in a specified card type
     * @param name             the user name
     * @param cardType         the type of registration card: Vmax, Vlibre, none
     */
    public User(String name, String cardType) throws Exception {
        this.name = name;
        if (cardType.equalsIgnoreCase(RegistrationCardType.Vlibre.toString())) {
        	this.registrationCard = new VlibreRegistrationCard();
        }
        else if (cardType.equalsIgnoreCase(RegistrationCardType.Vmax.toString())) {
        	this.registrationCard = new VmaxRegistrationCard();
        }
        else if (cardType.equalsIgnoreCase(RegistrationCardType.none.toString())) {
        	this.registrationCard = new NoRegistrationCard();
        	}
        else {
        	throw new Exception("Error: this type of registration card: " + cardType +" is not suppported");
        }
    }  
    // Methods
    // ----------------------- Update records (charges, credit time ...) -----------
    /**
     * Adds a charge to the total of all charges paid by the user.
     * @param charge the ride charge to add
     */
    public void addCharge(double charge) {
        this.setTotalCharges(this.getTotalCharges() + charge);
    }
    /**
     * Adds a time credit to the total owned by the user
     * @param time credit time used to reduce charge
     */
    public void addTimeCredit(double time) {
    	if(time < 0) {
            System.out.println("the time credit added should be positive.");
    	}
        else{
        	this.totalTimeCredit += time;
        	}
    }
    /**
     * Removes a time credit to the total owned by the user
     * @param time gained time
     */
    public void removeTimeCredit(double time) {
        if (this.totalTimeCredit - time < 0) {
            System.out.println("Time credit balance can't be negative");
        } else {
            this.totalTimeCredit -= time;
        }
    }
    // ------------------------------- Rent a bike action ----------------------------------------------------
    /**
     * Rents, if possible, a bike of a specific type from a certain station
     * at a certain time
     * @param Station  	   the station where a bike is desired to be taken
     * @param bicycleType  the desired bicycle type
     * @param rentDateTime the rent time
     * @throws Exception throw exception if the bicycle drop is not a valid
     * action for the user in this station taking into account the bicycle type
     */
    public synchronized void rent(Station station, BicycleType bicycleType, LocalDateTime rentDateTime) throws Exception{
    	// Check if the station is offline
    	if (rentDateTime == null) {
    		rentDateTime = LocalDateTime.now();
    	}
    	if(!station.isOnService()) {
    		throw new Exception("Error: can't rent a bicycle from a station out of service");
    	}
    	else {
        	Slot slot = station.getSlotWithBicycleOfType(bicycleType);
        	// Check if there is a slot containing the desired bicycle
        	if(slot == null) {
        		throw new Exception("Error: No parking slot adequat found with this bicyle of type: " + bicycleType);
        	}
        	else {
        		// Ensure that the user doen't hold a bicycle already
                if (this.rentedBicycle == null){
        			// Update statistics of station
        			station.setTotalRents(station.getTotalRents() + 1);
                	// Declare the rented bicycle
                    this.rentedBicycle = slot.getBicycle();
                    // Free the slot
                    slot.setStatus(SlotStatus.Free);
                    slot.setBicycle(null);
                    // Register current ride start time
                    this.rentDateTime = rentDateTime;
                    System.out.println("Bicycle rented successfully.");
                }
                else {
                    System.out.println("Error: a user can only rent at most one bicycle");
                }
            }
    	}
    }
    /**
     * Rents, if possible, a bike of a specific type from a certain station
     * at the exact date time this method was called.
     * @param Station  	   the station where a bike is desired to be taken
     * @param bicycleType  the desired bicycle type
     * @param rentDateTime the rent time
     * @throws Exception throw exception if the bicycle drop is not a valid
     * action for the user in this station taking into account the bicycle type
     */
    public void rent(Station station, BicycleType bicycleType) throws Exception{
        this.rent(station, bicycleType, LocalDateTime.now());
    }
    // ------------------------------------- Compute Ride Cost ------------------------------------------------------
    /**
     * Computes the cost of a ride after ended
     * @param bicycleType    the type of the bicycle rented
     * @param duration       the ride duration, supposed in minutes
     * @return the ride cost
     * @throws Exception throws exception in some inconsistency cases
     */
    public synchronized Invoice computeCost(BicycleType bicycleType, double duration) throws Exception  {
        RegistrationCardVisitor visitor = new ConcreteRegistrationCardVisitor();
    	this.getRegistrationCard().setChargedTimeToBePaid(duration);
    	this.getRegistrationCard().setCurrentBicycleType(bicycleType);        
        return this.getRegistrationCard().accept(visitor);
    }
    /**
     * Computes the cost of a ride having the r
     * @param bicycleType    the type of the bicycle rented
     * @param rentDateTime   the rent date time
     * @param returnDateTime the return date time
     * @return the ride cost
     * @throws Exception throws exception in some inconsistency cases
     */
    public synchronized Invoice computeCost(BicycleType bicycleType, LocalDateTime rentDateTime, LocalDateTime returnDateTime) throws Exception  {
        double rideDurationInMinutes = (double) rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);
        RegistrationCardVisitor visitor = new ConcreteRegistrationCardVisitor();
    	this.getRegistrationCard().setChargedTimeToBePaid(rideDurationInMinutes);
    	this.getRegistrationCard().setCurrentBicycleType(bicycleType);    
        return this.getRegistrationCard().accept(visitor);
    }
    // ------------------------------- Drop bicycle Action ------------------------------------------
    /**
     * Drops a bicycle with a ride duration in a station
     * @param bicycle   the bicycle to drop
     * @param Station  	     the station where a bike is desired to be dropped
     * @param duration  the duration of ride
     * @throws Exception throws exception in some inconsistency cases
     */
    public void drop(Station station, double duration) throws Exception {
    	// Check that the user holds a bicycle
        if (this.rentedBicycle == null){
            throw new Exception("Error: this user has no bicycle to drop.");
        }
    	// Check if the station is onService
        else if(!station.isOnService()) {
    		throw new Exception("Error: can't drop a bicycle in a station out of service");
    	}
        else {
        	Slot slot = station.getSlotOfStatus(SlotStatus.Free);
        	// Check if there is a free slot
        	if (slot == null) {
                throw new Exception("Error: No free parking slot in this station");
        	}
        	else {
        		synchronized(this){
        			// Update statistics of station
        			station.setTotalDrops(station.getTotalDrops() + 1);
        			// Set the slot
                    slot.setStatus(SlotStatus.Occupied);
                    slot.setBicycle(rentedBicycle);
                    // Charge the user and deduce used creditTime
                    Invoice invoice = computeCost(rentedBicycle.getType(),duration);
                    this.addCharge(invoice.getCharge());
                    this.removeTimeCredit(invoice.getUsedTimeCredit());
                    // Update statistics
                    this.totalRides ++;
                    this.totalRentTime += duration;
                    // Give the user the bonus (+5 in Plus station)
                    this.addTimeCredit(station.getTimeCreditBonus());
                    // Clear the user
                    this.rentedBicycle = null;
                    this.rentDateTime = null;
                    System.out.println("Bicycle successfully parked and the charge is: " + invoice.getCharge());	
        		}
        	}
        }
    }
    /**
     * Drops a bicycle at a specific time in a specified
     * station.
     * @param Station  	     the station where a bike is desired to be dropped
     * @param returnDateTime the return date time
     * @throws Exception throws exception in some inconsistency cases
     * (endDate < startTime, offline station, no free slot...)
     */
    public synchronized void drop(Station station, LocalDateTime returnDateTime) throws Exception{
    	// Check that return time is greater than the rent time
        if (returnDateTime.isBefore(this.rentDateTime)){
            throw new Exception("Error: can drop a bike before renting it");
        }
        else {
        	double duration = this.rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);
        	this.drop(station,duration);
        }
    }
    /**
     * Drops a bicycle at the exact time this method was called.
     * station.
     * @param Station  	     the station where a bike is desired to be dropped
     * @param returnDateTime the return date time
     * @throws Exception throws exception in some inconsistency cases (offline station, no free slot...)
     */
    public void drop(Station station) throws Exception {
        this.drop(station, LocalDateTime.now());
    }
    // ----------------------------- Ride Planning ---------------------------------------------
    /**
     * Propose a start station and an end station for a user taking in consideration
     * his current location (GPS) and his desired destination
     * @param bicycleType        the desired bicycle type
     * @param planningStrategy   the strategy to adopt for stations' proposition
     * @param endLocation        the end Location
     */
    public RideProposition proposeRide(Network network,BicycleType bicycleType, RidePlanningAlgorithm planningStrategy, Point endLocation) {
    	Point startLocation = this.coordinate;
    	RideProposition proposition = planningStrategy.getRideProposition(network,bicycleType, startLocation, endLocation);
        return proposition;
    }
    // Override Methods
    @Override
    public String toString() {
        String baseString = "User " + name + " [id:" + id + "]\n" +
                "[Location : " + coordinate +  "]\n" +
                "[Credit card : " + creditCardNumber + ", total charges : " + totalCharges + "]\n";
        if (this.registrationCard instanceof NoRegistrationCard) {
            return baseString + "No registration card.";
        } else {
            return baseString + "Registration card : " + registrationCard +
                    ", time credit balance : " + totalTimeCredit;
        }
    }
    // Getters and Setters
	public static int getUsersCount() {
		return usersCount;
	}
	public static void setUsersCount(int usersCount) {
		User.usersCount = usersCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}
	public RegistrationCard getRegistrationCard() {
		return registrationCard;
	}
	public void setRegistrationCard(RegistrationCard registrationCard) {
		this.registrationCard = registrationCard;
	}
	public double getTotalRides() {
		return totalRides;
	}
	public void setTotalRides(double totalRides) {
		this.totalRides = totalRides;
	}
	public double getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}
	public double getTotalTimeCredit() {
		return totalTimeCredit;
	}
	public void setTotalTimeCredit(double totalTimeCredit) {
		this.totalTimeCredit = totalTimeCredit;
	}
	public double getTotalRentTime() {
		return totalRentTime;
	}
	public void setTotalRentTime(double totalRentTime) {
		this.totalRentTime = totalRentTime;
	}
	public Bicycle getRentedBicycle() {
		return rentedBicycle;
	}
	public void setRentedBicycle(Bicycle rentedBicycle) {
		this.rentedBicycle = rentedBicycle;
	}
	public LocalDateTime getRentDateTime() {
		return rentDateTime;
	}
	public void setRentDateTime(LocalDateTime rentDateTime) {
		this.rentDateTime = rentDateTime;
	}
	public int getId() {
		return id;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
}

