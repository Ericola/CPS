package tests.implemError;

import implementations.MurailleImplem;


import org.junit.Before;

import tests.AbstractMuraille;

public class MurailleTestsError extends AbstractMuraille {

	@Override
	@Before
	public void before() {
		muraille = new MurailleImplem();	
	}

}
