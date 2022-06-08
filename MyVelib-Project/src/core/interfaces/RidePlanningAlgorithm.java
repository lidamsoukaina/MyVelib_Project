package core.interfaces;

import java.awt.Point;

import core.classes.Network;
import core.classes.RideProposition;
import core.enumerations.BicycleType;

public interface RidePlanningAlgorithm {
	RideProposition getRideProposition(Network network,BicycleType bicycleType, Point startLocation, Point endLocation);
}