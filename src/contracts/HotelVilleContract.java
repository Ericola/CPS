package contracts;

import decorators.HotelVilleDecorator;
import decorators.MineDecorator;
import services.IHotelVilleService;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class HotelVilleContract extends HotelVilleDecorator{
	public HotelVilleContract(IHotelVilleService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estLaminee(M) =(min) orRestant(M) <=  0 */
		if(!(estLaminee() && (orRestant() <= 0))){
			throw new InvariantError("estLaminee(M) =(min) orRestant(M) <=  0 incorrecte");
		}
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

	public int orRestant(){
		// aucun pre 

		// run
		return super.orRestant();
	}

	public boolean estLaminee(){
		// aucun pre 

		// run
		return super.estLaminee();
	}

	public IHotelVilleService init(int largeur, int hauteur){

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

		/* post getLargeur(init(l, h)) = l */
		if(!(getLargeur() == largeur)){
			throw new PostconditionError("getLargeur(init(l, h)) = l incorrecte.");
		}

		/* post getHauteur(init(l, h)) = h */
		if(!(getHauteur() == hauteur)){
			throw new PostconditionError("getHauteur(init(l, h)) = h incorrecte.");
		}

		/* post orRestant(init(l, h)) = 51 */
		if(!(orRestant() == 51)){
			throw new PostconditionError("orRestant(init(l, h)) = 51 incorrecte.");
		}

		return this;
	}

	public  IHotelVilleService depot(int s){
		/* pre depot(M, s)) require !estLaminee(M) */
		if((estLaminee())){
			throw new PreconditionError("retrait(M, s)) require !estLaminee(M) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.depot(s);

		// inv apres
		checkInvariants();

		/* post orRestant(depot(M,s)) == orRestant(M) + s */
		if(!(orRestant() == (oldOrRest + s))){
			throw new PostconditionError("orRestant(depot(M,s)) == orRestant(M) + s incorrecte");
		}

		return this;

	}
}
