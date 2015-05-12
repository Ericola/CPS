package contracts;


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

		// pre init(l, h) require l > 0
		if(!( largeur > 0)){
			throw new PreconditionError(" init(l, h) require l > 0");
		}

		// pre init(l, h) require h > 0
		if(!( hauteur > 0)){
			throw new PreconditionError("init(l, h) require h > 0");
		}

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
