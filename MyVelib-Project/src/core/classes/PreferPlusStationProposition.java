package core.classes;

import java.awt.Point;
import java.util.List;

import core.enumerations.BicycleType;
import core.enumerations.StationType;
import core.interfaces.RidePlanningAlgorithm;

/**
 * Class for planning ride suggestion strategy that prefer -plus- stations
 * @version 1.0
 */
public class PreferPlusStationProposition implements RidePlanningAlgorithm {
	@Override
	public RideProposition getRideProposition(Network network,BicycleType bicycleType, Point startLocation, Point endLocation) {
		RideProposition rideProposition = new RideProposition();
        List<Station> allAvailableStatiobs = network.getListStations();
        // Station
        Station bestStartStation = null;
        Station bestEndStation = null;
        double bestStartDistance = Double.POSITIVE_INFINITY;
        double bestEndDistance = Double.POSITIVE_INFINITY;
        //Plus stations records
        Station bestPlusStartStation = null;
        Station bestPlusEndStation = null;
        double bestPlusStartDistance = Double.POSITIVE_INFINITY;
        double bestPlusEndDistance = Double.POSITIVE_INFINITY;
        if(allAvailableStatiobs.size() == 0){
            System.out.println("No station in this network");
        }
        for (Station station : allAvailableStatiobs) {
        	Point coordinates = station.getCoordinate();
        	// closed station to the start location
        	double distanceToStart = Math.sqrt(Math.pow(coordinates.getX() - startLocation.getX(), 2) + Math.pow(coordinates.getY() - startLocation.getY(), 2));
            if (distanceToStart < bestStartDistance && station.hasBicycleOfType(bicycleType) && station.isOnService()) {
            	bestStartDistance = distanceToStart;
            	bestStartStation = station;
            	if (station.getType() == StationType.Plus) {
            		bestPlusStartStation = station;
            		bestPlusStartDistance = distanceToStart;
            	}
            }
        	// closed station to the end location
        	double distanceToEnd = Math.sqrt(Math.pow(coordinates.getX() - endLocation.getX(), 2) + Math.pow(coordinates.getY() - endLocation.getY(), 2));
            if (distanceToEnd < bestEndDistance && station.hasBicycleOfType(bicycleType) && station.isOnService()) {
            	bestEndDistance = distanceToStart;
            	bestEndStation = station;
            	if (station.getType() == StationType.Plus) {
            		bestPlusEndStation = station;
            		bestPlusEndDistance = distanceToStart;
            	}
            }
        }
        rideProposition.setStartStation(bestStartStation);
        rideProposition.setEndStation(bestEndStation);
        if (bestPlusEndDistance <= 1.1 * bestEndDistance) {
            rideProposition.setEndStation(bestPlusEndStation);;
        }
        if (bestPlusStartDistance <= 1.1 * bestStartDistance) {
            rideProposition.setEndStation(bestPlusStartStation);;
        }
        return rideProposition;
	}
}
