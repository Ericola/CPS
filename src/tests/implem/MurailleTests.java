package tests.implem;

import implementations.MurailleImplem;


import org.junit.Before;

import tests.AbstractMuraille;

public class MurailleTests extends AbstractMuraille {

	@Override
	@Before
	public void before() {
		muraille = new MurailleImplem();	
	}

}
