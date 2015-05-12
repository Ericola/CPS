package tests.implemError;

import implementationsError.MurailleImplemError;

import org.junit.Before;

import tests.AbstractMuraille;

public class MurailleTestsError extends AbstractMuraille {

	@Override
	@Before
	public void before() {
		muraille = new MurailleImplemError();	
	}

}
