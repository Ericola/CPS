package contracts;


import services.IMurailleService;
import decorators.MurailleDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;


public class MurailleContract extends MurailleDecorator {
	public MurailleContract(IMurailleService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estMort(V) =(min) getpdv(V) <= 0 */
		if(!(estMort() == (getPdv() <= 0))){
			throw new InvariantError("estMort(V) = getpdv(V) <= 0 incorrecte");
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

	public int getPdv() {
		// aucun pre 

		// run
		return super.getPdv();
	}

	
	public boolean estMort(){
		// aucun pre 

		// run
		return super.estMort();
	}
	
	public IMurailleService init(int largeur, int hauteur, int pdv){

		// pre init(l, h, pdv) require largeur %2 = 1
		if(!( (largeur %2) == 1)){
			throw new PreconditionError("init(largeur, hauteur, pdv) require largeur % 2 = 1.");
		}

		// pre init(l, h, pdv) require hauteur %2 = 1
		if(!( (hauteur %2) == 1)){
			throw new PreconditionError("init(largeur, hauteur, pdv) require hauteur % 2 = 1.");
		}

		// pre init(l, h, pdv) require 0 < pdv
		if(!(0 < pdv)){
			throw new PreconditionError("init(largeur, hauteur,pdv) require 0 < pdv");
		}

		// inv avant 
		checkInvariants();

		// run
		super.init(largeur, hauteur, pdv);

		// inv apres
		checkInvariants();

		// post getHauteur(init(largeur, hauteur, pdv)) = hauteur
		if (!(getHauteur() == hauteur)) {
			throw new PostconditionError("getHauteur(init(largeur, hauteur, pdv) = hauteur incorrecte.");
		}

		// post getLargeur(init(largeur, hauteur, pdv)) = largeur
		if (!(getLargeur() == largeur)) {
			throw new PostconditionError("getLargeur(init(largeur, hauteur, pdv) = largeur incorrecte.");
		}



		// post getPdv(init(largeur, hauteur, pdv)) = pdv
		if (!(getPdv() == pdv)) {
			throw new PostconditionError("getPdv(init(largeur, hauteur, pdv) = pdv incorrecte.");
		}

		return this;
	}

	public IMurailleService retrait(int s) {

		// pre getPdv(retrait(V, s)) require s > 0 AND !estMort(V)
		if (!(s > 0 && !estMort())) {
			throw new PostconditionError("getPdv(retrait(V, s)) require s > 0 AND !estMort(V) incorrecte");
		}
		
		// inv avant 
		checkInvariants();

		// capture 
		int oldPdv = getPdv();
		
		// run
		super.retrait(s);

		// inv apres
		checkInvariants();
		
		
		// post getPdv(retrait(V, s)) = getPdv(V)-s
		if (!(getPdv() == oldPdv - s)) {
			throw new PostconditionError("getPdv(retrait(V, s)) = getPdv(V)-s incorrecte");
		}	
		
		return this;
	}
}
