package core.classes;

import core.interfaces.RegistrationCardVisitor;

public class VlibreRegistrationCard extends RegistrationCard {
	@Override
	public Invoice accept(RegistrationCardVisitor visitor) throws Exception {
		return visitor.visit(this);
	}
}
