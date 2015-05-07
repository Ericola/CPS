package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMineService;

public abstract class AbstractMine extends AbstractAssertion {

	protected IMineService mine;

	@Before
	public abstract void before();

	@After
	public void after() {
		mine = null;
	}


	@Test
	public void testInitPositif() {
		
	}
	
}
