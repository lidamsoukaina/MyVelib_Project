package core.classes;
import java.util.Objects;

import core.enumerations.BicycleType;
import core.interfaces.RegistrationCardVisitor;

/**
 * Class for registration or not card
 * @version 1.0
 */
public abstract class RegistrationCard {
	// Attributes
    private static int cardsCount = 0;
    private final int id = generateUniqueID();
    private BicycleType currentBicycleType;
    private double chargedTimeToBePaid;
    private User holder;
	// Methods
    /**
     * Generates unique id for each card
     */
    private int generateUniqueID() {
        return ++cardsCount;
    }
    /**
     * visitor pattern to return ride cost and deduced time credit
     */
    public abstract Invoice accept(RegistrationCardVisitor visitor) throws Exception;
    // Setters
	public void setChargedTimeToBePaid(double chargedTimeToBePaid) {
		this.chargedTimeToBePaid = chargedTimeToBePaid;
	}
	public void setCurrentBicycleType(BicycleType currentBicycleType) {
		this.currentBicycleType = currentBicycleType;
	}
	public void setHolder(User holder) {
		this.holder = holder;
	}
    // Getters
    public int getId() {
        return id;
    }
    public static int getCardsCount() {
		return cardsCount;
	}
	public double getChargedTimeToBePaid() {
		return chargedTimeToBePaid;
	}
	public BicycleType getCurrentBicycleType() {
		return currentBicycleType;
	}
	public User getHolder() {
		return holder;
	}
    // Override Methods
    @Override
    public String toString() {
        return "Card of id = " + id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCard card = (RegistrationCard) o;
        return id == card.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
