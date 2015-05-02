package contracts;

import services.IMineService;
import services.IRouteService;
import decorators.RouteDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class RouteContract extends RouteDecorator {


	public RouteContract(IRouteService delegate) {
		super(delegate);
	}

	private void checkInvariants() {

	}
	
	public IRouteService init(int largeur, int hauteur){

		// pre init(l, h) require largeur %2 = 1
		if(!( (largeur %2) == 1)){
			throw new PreconditionError(" init(l, h) require largeur % 2 = 1.");
		}

		// pre init(l, h) require largeur %2 = 1
		if(!( (hauteur %2) == 1)){
			throw new PreconditionError("init(l, h) require hauteur % 2 = 1.");
		}

		// inv avant 
		checkInvariants();

		// run
		super.init(largeur, hauteur);

		// inv apres
		checkInvariants();

		return this;
	}


	public int getLargeur() {
		// aucun pre 

		// run
		return super.getLargeur();
	}


	public int getHauteur() {
		// aucun pre 

		// run
		return super.getHauteur();
	}
}
