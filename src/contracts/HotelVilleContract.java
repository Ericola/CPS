package contracts;

import decorators.HotelVilleDecorator;
import decorators.MineDecorator;
import services.IHotelVilleService;
import services.IMineService;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class HotelVilleContract extends HotelVilleDecorator{
	public HotelVilleContract(IHotelVilleService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estLaminee(H) =(min) orRestant(H) <=  0 */
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
		/* pre depot(H, s)) require !estLaminee(H) */
		if((estLaminee())){
			throw new PreconditionError("retrait(M, s)) require !estLaminee(H) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.depot(s);

		// inv apres
		checkInvariants();

		/* post orRestant(depot(H,s)) == orRestant(H) + s */
		if(!(orRestant() == (oldOrRest + s))){
			throw new PostconditionError("orRestant(depot(M,s)) == orRestant(M) + s incorrecte");
		}

		return this;

	}
	
	@Override
	public IHotelVilleService setOrRestant(int s) {
		// pre setOrRestant(H, s) require s > 0
		if (!(s > 0)) {
			throw new PreconditionError("setOrRestant(H, s) require s > 0 incorrecte");
		}
		
		// capture 
		int oldOrRestant = orRestant();
		
		// inv avant 
		checkInvariants();
		
		// run
		super.setOrRestant(s);
		
		// inv apres
		checkInvariants();
		
		//post orRestant(setOrRestant(H, s)) = orRestant(H)
		if (!(orRestant() == oldOrRestant)) {
			throw new PostconditionError("orRestant(setOrRestant(H, s)) = orRestant(H) incorrecte");
		}	
		
		//post orRestant(setOrRestant(H, s)) = s
		if (!(orRestant() == s)) {
			throw new PostconditionError("orRestant(setOrRestant(H, s)) = s incorrecte");
		}	
		
		return this;
	}
}
