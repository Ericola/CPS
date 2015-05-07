package tests.implemError;

import implementations.MineImplem;
import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractMine;
import tests.AbstractVillageois;

public class MineTestsError extends AbstractMine {

	@Override
	@Before
	public void before() {
		mine = new MineImplem();
		
	}

}
