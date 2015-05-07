package tests.implemError;

import implementations.MoteurJeuImplem;

import org.junit.Before;

import tests.AbstractMoteurJeu;
import tests.AbstractVillageois;

public class MoteurJeuTestsError extends AbstractMoteurJeu {

	@Override
	@Before
	public void before() {
		moteurJeu = new MoteurJeuImplem();	
	}

}
