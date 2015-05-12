package tests.contracts;



import implementations.VillageoisImplem;
import org.junit.Before;
import org.junit.Test;
import contracts.VillageoisContract;
import exceptions.PreconditionError;
import tests.AbstractVillageois;

public class RouteContractTests extends AbstractVillageois{
	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImplem());
	}

}
