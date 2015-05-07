package tests.implemError;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.implem.HotelVilleTests;
import tests.implem.MineTests;
import tests.implem.MoteurJeuTests;
import tests.implem.MurailleTests;
import tests.implem.RouteTests;
import tests.implem.VillageoisTests;


@RunWith(Suite.class)
@SuiteClasses({
	HotelVilleTestsError.class, 
	RouteTestsError.class, 
	MurailleTestsError.class, 
	MoteurJeuTestsError.class, 
	VillageoisTestsError.class,
	MineTestsError.class})
public class AllTestsImplemError {
}
