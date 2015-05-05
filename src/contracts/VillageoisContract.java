package contracts;


import services.IVillageoisService;
import decorators.VillageoisDecorator;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;


public class VillageoisContract extends VillageoisDecorator {
	public VillageoisContract(IVillageoisService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estMort(V) =(min) getpdv(V) <= 0 */
		if(!(estMort() && (getPdv() <= 0))){
			throw new InvariantError("estMort(V) = getpdv(V) <= 0 incorrecte");
		}
	}

	public ERace getRace() {
		// aucun pre 

		// run
		return super.getRace();
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


	public int getForce() {
		// aucun pre 

		// run
		return super.getForce();
	}


	public int getVitesse() {
		// aucun pre 

		// run
		return super.getVitesse();
	}


	public int getPdv() {
		// aucun pre 

		// run
		return super.getPdv();
	}


	public int getQtor() {
		// aucun pre 

		// run
		return super.getQtor();
	}
	
	public boolean estMort(){
		// aucun pre 

		// run
		return super.estMort();
	}

	public IVillageoisService init(ERace type, int largeur, int hauteur,
			int force, int vitesse, int pdv){

		// pre init(t,l,h,f,v,pdv) require largeur %2 = 1
		if(!( (largeur %2) == 1)){
			throw new PreconditionError("init(type,largeur, hauteur, force, vitesse, pdv) require largeur % 2 = 1.");
		}

		// pre init(t,l,h,f,v,pdv) require largeur %2 = 1
		if(!( (hauteur %2) == 1)){
			throw new PreconditionError("init(type,largeur, hauteur, force, vitesse, pdv) require hauteur % 2 = 1.");
		}

		// pre init(t,l,h,f,v,pdv) require 0 < force < pdv
		if(!(0 < force && force < pdv)){
			throw new PreconditionError("init(type,largeur, hauteur, force, vitesse, pdv) require 0 < force < pdv");
		}

		// pre init(t,l,h,f,v,pdv) require 0 <= vitesse
		if(!(0 <= vitesse)){
			throw new PreconditionError("init(type,largeur, hauteur, force, vitesse, pdv) require 0 <= vitesse");
		}

		// inv avant 
		checkInvariants();

		// run
		super.init(type,largeur, hauteur, force, vitesse, pdv);

		// inv apres
		checkInvariants();

		// post getHauteur(init(type,largeur, hauteur, force, vitesse, pdv)) = hauteur
		if (!(getHauteur() == hauteur)) {
			throw new PostconditionError("getHauteur(init(type,largeur, hauteur, force, vitesse, pdv) = hauteur incorrecte.");
		}

		// post getLargeur(init(type,largeur, hauteur, force, vitesse, pdv)) = largeur
		if (!(getLargeur() == largeur)) {
			throw new PostconditionError("getLargeur(init(type,largeur, hauteur, force, vitesse, pdv) = largeur incorrecte.");
		}

		// post getForce(init(type,largeur, hauteur, force, vitesse, pdv)) = force
		if (!(getForce() == force)) {
			throw new PostconditionError("getForce(init(type,largeur, hauteur, force, vitesse, pdv) = force incorrecte.");
		}

		// post getVitesse(init(type,largeur, hauteur, force, vitesse, pdv, qtor)) = vitesse
		if (!(getVitesse() == vitesse)) {
			throw new PostconditionError("getVitesse(init(type,largeur, hauteur, force, vitesse, pdv) = vitesse incorrecte.");
		}

		// post getPdv(init(type,largeur, hauteur, force, vitesse, pdv)) = pdv
		if (!(getPdv() == pdv)) {
			throw new PostconditionError("getPdv(init(type,largeur, hauteur, force, vitesse, pdv) = pdv incorrecte.");
		}

		// post getQtor(init(type,largeur, hauteur, force, vitesse, pdv)) = 0
		if (!(getQtor() == 0)) {
			throw new PostconditionError("getQtor(init(type,largeur, hauteur, force, vitesse, pdv, qtor) = 0 incorrecte.");
		}

		return this;
	}

	public IVillageoisService retrait(int s) {

		// pre getPdv(retrait(V, s)) require s > 0 AND !estMort(V)
		if (!(s > 0 && !estMort())) {
			throw new PostconditionError("getPdv(retrait(V, s)) require s > 0 AND !estMort(V) incorrecte");
		}
		
		// inv avant 
		checkInvariants();

		// capture 
		int oldPdv = getPdv();
		int oldQtor = getQtor();
		
		// run
		super.retrait(s);

		// inv apres
		checkInvariants();
		
		
		// post getPdv(retrait(V, s)) = getPdv(V)-s
		if (!(getPdv() == oldPdv - s)) {
			throw new PostconditionError("getPdv(retrait(V, s)) = getPdv(V)-s incorrecte");
		}	
	
		// post getQtor(retrait(V, s)) = getQtor(V)
		if (!(getQtor() == oldQtor)) {
			throw new PostconditionError("getQtor(retrait(V, s)) = getQtor(V) incorrecte");
		}	
		
		return this;
	}
	

	@Override
	public IVillageoisService setQtor(int s) {
		// pre setQtor(V, s) require s > 0
		if (!(s > 0)) {
			throw new PreconditionError("setQtor(V, s) require s > 0 incorrecte");
		}
		
		// capture 
		int oldQtor = getQtor();
		
		// inv avant 
		checkInvariants();
		
		// run
		super.setQtor(s);
		
		// inv apres
		checkInvariants();
		
		//post getQtor(setQtor(V, s)) = getQtor(V)
		if (!(getQtor() == oldQtor)) {
			throw new PostconditionError("getQtor(setQtor(V, s)) = getQtor(V) incorrecte");
		}	
		
		//post getQtor(setQtor(V, s)) = s
		if (!(getQtor() == s)) {
			throw new PostconditionError("getQtor(setQtor(V, s)) = s incorrecte");
		}	
		
		return this;
	}
}
