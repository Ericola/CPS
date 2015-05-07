package tests.contracts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	HotelVilleContractTests.class, 
	RouteContractTests.class, 
	MurailleContractTests.class, 
	MoteurJeuContractTests.class, 
	MineContractTests.class,
	VillageoisContractTests.class})
public class AllTestsContracts {
}
