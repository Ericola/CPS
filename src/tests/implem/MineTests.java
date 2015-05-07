package tests.implem;

import implementations.MineImplem;
import implementations.VillageoisImplem;

import org.junit.Before;

import tests.AbstractMine;
import tests.AbstractVillageois;

public class MineTests extends AbstractMine {

	@Override
	@Before
	public void before() {
		mine = new MineImplem();
		
	}

}
