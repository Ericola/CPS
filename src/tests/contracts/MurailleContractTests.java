package tests.contracts;



import implementations.MurailleImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.MurailleContract;
import exceptions.PreconditionError;
import tests.AbstractMuraille;

public class MurailleContractTests extends AbstractMuraille{
	@Override
	@Before
	public void before() {
		muraille = new MurailleContract(new MurailleImplem());
	}
	
}
