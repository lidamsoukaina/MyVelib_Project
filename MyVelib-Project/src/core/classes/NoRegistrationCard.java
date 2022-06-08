package core.classes;

import core.interfaces.RegistrationCardVisitor;

/**
 * Class for having no registration card representation
 * @version 1.0
 */
public class NoRegistrationCard extends RegistrationCard {
	@Override
	public Invoice accept(RegistrationCardVisitor visitor) throws Exception {
		return visitor.visit(this);
	}
}