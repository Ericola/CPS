package tests.contracts;



import implementations.MineImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.MineContract;
import exceptions.PreconditionError;
import tests.AbstractMine;

public class MineContractTests extends AbstractMine{
	@Override
	@Before
	public void before() {
		mine = new MineContract(new MineImplem());
	}
}
