package core.interfaces;

import core.classes.Invoice;
import core.classes.NoRegistrationCard;
import core.classes.VlibreRegistrationCard;
import core.classes.VmaxRegistrationCard;

public interface RegistrationCardVisitor {
	Invoice visit(NoRegistrationCard card) throws Exception;
	Invoice visit(VmaxRegistrationCard card) throws Exception;
	Invoice visit(VlibreRegistrationCard card) throws Exception;
}
