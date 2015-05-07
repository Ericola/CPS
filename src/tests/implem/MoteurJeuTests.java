package tests.implem;

import implementations.MoteurJeuImplem;

import org.junit.Before;

import tests.AbstractMoteurJeu;
import tests.AbstractVillageois;

public class MoteurJeuTests extends AbstractMoteurJeu {

	@Override
	@Before
	public void before() {
		moteurJeu = new MoteurJeuImplem();	
	}

}
