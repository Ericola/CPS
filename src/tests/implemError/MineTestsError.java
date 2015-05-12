package tests.implemError;

import implementationsError.MineImplemError;

import org.junit.Before;

import tests.AbstractMine;

public class MineTestsError extends AbstractMine {

	@Override
	@Before
	public void before() {
		mine = new MineImplemError();
		
	}

}
