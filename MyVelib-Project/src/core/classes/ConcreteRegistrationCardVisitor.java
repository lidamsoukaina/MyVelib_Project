package core.classes;

import core.enumerations.BicycleType;
import core.interfaces.RegistrationCardVisitor;

public class ConcreteRegistrationCardVisitor implements RegistrationCardVisitor{
	@Override
	public Invoice visit(NoRegistrationCard card) throws Exception {
		Invoice invoice = new Invoice(0,0);
		int cost = 0;
		int hourCost = 1;
		int hours = (int) card.getChargedTimeToBePaid()/60;
		BicycleType bicycleType = card.getCurrentBicycleType();
		if (bicycleType == BicycleType.Mechanical) {
			cost += hours*hourCost;
			invoice.setCharge(cost);
			System.out.println(bicycleType);
			return invoice;
			}
		else if (bicycleType == BicycleType.Electrical) {
			cost += (2*hours);
			invoice.setCharge(cost);
			return invoice;
			}
		else if (bicycleType == null) {throw new Exception("No bicycleType registred for the possible current ride");}
		else {throw new Exception("Bicycle Type " + bicycleType + " not supported" );}
	}
	
	@Override
	public Invoice visit(VlibreRegistrationCard card) throws Exception {
		Invoice invoice = new Invoice(0,0);
		int firstHourCost = 0;
		double minutes = card.getChargedTimeToBePaid();
		double postFirstHourMinutes = minutes - 60;
		int cost = firstHourCost;
		BicycleType bicycleType = card.getCurrentBicycleType();
		if ( bicycleType == null ) {throw new Exception("No bicycleType registred for the possible current ride");}
		else if (postFirstHourMinutes > 0) {
			double timeCanBeReduced = minutes - 60;
			double userCredit = card.getHolder().getTotalTimeCredit();
			double deducedTimeFromCredtTime = Math.min(userCredit,postFirstHourMinutes);
			postFirstHourMinutes = Math.max(0,timeCanBeReduced - userCredit);
			int postFirstHourHours = (int) postFirstHourMinutes/60;
			invoice.setUsedTimeCredit((int)deducedTimeFromCredtTime);
			if (bicycleType == BicycleType.Mechanical) {
				int postFirstHourCost = 1;
				cost += postFirstHourHours * postFirstHourCost;
				System.out.println(postFirstHourHours);
				invoice.setCharge(cost);
				return invoice;
			}
			else if (bicycleType == BicycleType.Electrical) {
				int postFirstHourCost = 2;
				cost += postFirstHourHours * postFirstHourCost;
				invoice.setCharge(cost);
				return invoice;
			}
			else{
				throw new Exception("The bicycle type " + bicycleType + " not supported");
				}
			}
		else {
			return invoice;
		}
	}
	@Override
	public Invoice visit(VmaxRegistrationCard card) {
		Invoice invoice = new Invoice(0,0);
		int firstHourCost = 0;
		int postFirstHourCost = 1;
		int cost = 0;
		double time = card.getChargedTimeToBePaid();
		int hours = (int) time/60;
		if (hours >= 1) {
			cost += firstHourCost + (hours-1)*postFirstHourCost;
		}
		invoice.setCharge(cost);
		return invoice;
	}
}
