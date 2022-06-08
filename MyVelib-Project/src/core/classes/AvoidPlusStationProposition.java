package core.classes;

import java.awt.Point;
import java.util.List;

import core.enumerations.BicycleType;
import core.enumerations.StationType;
import core.interfaces.RidePlanningAlgorithm;
/**
 * Class for planning ride suggestion strategy based on avoid “plus” stations: like minimal 
 * walking distance but return station cannot be a “plus” station
 * @version 1.0
 */
public class AvoidPlusStationProposition implements RidePlanningAlgorithm{
	@Override
	public RideProposition getRideProposition(Network network,BicycleType bicycleType, Point startLocation, Point endLocation) {
		RideProposition rideProposition = new RideProposition();
        List<Station> allAvailableStatiobs = network.getListStations();
        Station bestStartStation = null;
        Station bestEndStation = null;
        double bestStartDistance = Double.POSITIVE_INFINITY;
        double bestEndDistance = Double.POSITIVE_INFINITY;
        for (Station station : allAvailableStatiobs) {
        	if(station.getType() != StationType.Plus) {
            	Point coordinates = station.getCoordinate();
            	// closed station to the start location
            	double distanceToStart = Math.sqrt(Math.pow(coordinates.getX() - startLocation.getX(), 2) + Math.pow(coordinates.getY() - startLocation.getY(), 2));
                if (distanceToStart < bestStartDistance && station.hasBicycleOfType(bicycleType) && station.isOnService()) {
                	bestStartDistance = distanceToStart;
                	bestStartStation = station;

                }
            	// closed station to the end location
            	double distanceToEnd = Math.sqrt(Math.pow(coordinates.getX() - endLocation.getX(), 2) + Math.pow(coordinates.getY() - endLocation.getY(), 2));
                if (distanceToEnd < bestEndDistance && station.hasBicycleOfType(bicycleType) && station.isOnService()) {
                	bestEndDistance = distanceToStart;
                	bestEndStation = station;
                }
        	}
        }
        if(bestStartStation == null){
            System.out.println("No station of Plus type in this network");
        }
        rideProposition.setStartStation(bestStartStation);
        rideProposition.setEndStation(bestEndStation);
        return rideProposition;
	}
}