package core.classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import core.enumerations.BicycleType;
import core.enumerations.SlotStatus;

/**
 * Class for network representation
 * @version 1.0
 */
public class Network {
	// Attributes
    private static int networksCount;
    private final int id = generateUniqueID();
    private String name;
    private List<Station> listStations = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    // Constructors
    /**
     * Create a network
     * @param name		the name of the network
     * @throws Exception if the network already exists
     */
	public Network(String name) {
		this.name = name;
	}
    /**
     * Create a network with the specified number of stations uniformly distributed over a square
     * with a randomly distributed bikes over all stations
     * @param name		the name of the network
     * @param nstations the count of stations to be created in the network
     * @param nslots    the count of slots over each station
     * @param side 		the measure of side square
     * @param nbikes 	the initial population of bicycle percentage
     * @throws Exception In case the parameter nbikes isn't between 0 and 1
     */
	public Network(String name, int nstations, int nslots, double side, float nbikes) throws Exception {
		if( 0 > nbikes || nbikes > 1){
			throw new Exception("Error: the parameter nbikes shoud be between 0 and 1 instead is equal to :" + nbikes);
		}
        System.out.println("...Network creation");
		this.name = name;
        Random rand = new Random();
        List<Station> visitedStations = new ArrayList<>();
        Station station = null;
        List<Point> affectedPoints = new ArrayList<Point>();
        System.out.println("...Creation of stations ");
        for (int i = 0; i < nstations; i++) {
            while (station == null) {
                int x = rand.nextInt((int) side);
                int y = rand.nextInt((int) side);
                Point candidate = new Point(x,y);
                if (!affectedPoints.contains(candidate)) {
	                station = new Station(candidate);
	                station.addFreeSlots(nslots); // station with free slots
                }
            }
	        visitedStations.add(station);
	        station = null;
        }
        this.listStations = visitedStations;
        int bikesCount = (int) nbikes * nslots * nstations;
        // Creating the bikes
        System.out.println("...Creation of bicycles");
        // Create by default 70% mechanical bike // To do: create confi file whith these params
        int electricalBikesCount = (int) 0.7 * bikesCount;
        int mechanicalBikesCount = bikesCount - electricalBikesCount;

        for (int j = 0; j < electricalBikesCount; j++) {
            Bicycle bike = new Bicycle(BicycleType.Electrical);
            Boolean isParked = false;
            while(!isParked) {
	            int indexStation = rand.nextInt((int) nstations);
	            Station randomStation = visitedStations.get(indexStation);
            	Slot slot = randomStation.getSlotOfStatus(SlotStatus.Free);
            	if(slot != null) {
	            	slot.addBicycle(bike);
	            	isParked = true;
            	}
            }
        }
        for (int j = 0; j < mechanicalBikesCount; j++) {
            Bicycle bike = new Bicycle(BicycleType.Mechanical);
            Boolean isParked = false;
            while(!isParked) {
	            int indexStation = rand.nextInt((int) nstations);
	            Station randomStation = visitedStations.get(indexStation);
            	Slot slot = randomStation.getSlotOfStatus(SlotStatus.Free);
            	if(slot != null) {
	            	slot.addBicycle(bike);
	            	isParked = true;
            	}
            }
        }
        }
	// Methods
    /**
     * Generates unique id for each card
     */
    private int generateUniqueID() {
        return ++networksCount;
    }
    /**
     * Adds user to the network
     * @param user user to be added to the network
     */
    public void addUser(User user) {
    	listUsers.add(user);
    }
    /**
     * Adds station to the network
     * @param station station to be added to the network
     */
    public void addStation(Station station) {
    	listStations.add(station);
    }
    /**
    /**
     * 
     * Displays statistics about the station
     * @param station the studied station
     */
    public void displayStationBalance(Station station) {
        if (this.getListStations().contains(station)) {
            System.out.println("Station BALANCE: ");
            System.out.println(station);
        }
        else {
            System.out.println("Error: this station of id = " + station.getId() + " doesn't exist in this network");
        }
    }
    /**
     * Displays statistics about the user
     * @param user the studied user
     */
    public void displayUserBalance(User user) {
        if (this.getListUsers().contains(user)) {
            System.out.println("Station BALANCE: ");
            System.out.println(user);
        }
        else {
            System.out.println("Error: this user of id = " + user.getId() + " doesn't exist in this network");
        }
    }
    /**
     * Displays the entire status (stations, parking bays,users)
     * of an a myVelib network velibnetworkName
     */
    public void displayNetworkStatus() {
        System.out.println();
        System.out.println(this.getName()+ " STATUS: ");
        System.out.println(this);
        System.out.println();
    }
    /**
     * Sorts stations sorted w.r.t. to a provided sorting policy (comparator)
     */
    public List<Station> sortStation(Comparator<Station> comparator) {
    	Collections.sort(this.listStations, comparator);
        return this.listStations;
    }
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Station> getListStations() {
		return listStations;
	}
	public void setListStations(List<Station> listStations) {
		this.listStations = listStations;
	}
	public static int getNetworksCount() {
		return networksCount;
	}
	public int getId() {
		return id;
	}
	public List<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	// Override methods
    @Override
    public String toString() {
        return "MyVelibSystem{" + "\n"+
                "name=" + name + "\n"+
                "stations=" + listStations + "\n"+
                "users=" + listUsers +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network that = (Network) o;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
