package contracts;

import services.IMineService;
import decorators.MineDecorator;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class MineContract extends MineDecorator {

	public MineContract(IMineService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estLaminee(M) =(min) orRestant(M) <=  0 */
		if(!(estLaminee() && (orRestant() <= 0))){
			throw new InvariantError("estLaminee(M) =(min) orRestant(M) <=  0 incorrecte");
		}

		/* estAbandonnee(M) =(min) abandonCompteur(M) = 51 */
		if(!(estAbandonnee() && abandonCompteur() == 51)){
			throw new InvariantError("estAbandonnee(M) =(min) abandonCompteur(M) = 51 incorrecte");
		}

		/* 0 <= abandonCompteur(M) <= 51 */
		if(!(abandonCompteur() >= 0 && abandonCompteur() <= 51 )){
			throw new InvariantError("0 <= abandonCompteur(M) <= 51 incorrecte");
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
	public boolean estAbandonnee(){
		// aucun pre 

		// run
		return super.estAbandonnee();
	}
	public boolean estLaminee(){
		// aucun pre 

		// run
		return super.estLaminee();
	}

	public int abandonCompteur(){
		// aucun pre 

		return super.abandonCompteur();
	}

	public IMineService init(int largeur, int hauteur){

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


		/* post abandonCompteur(init(l, h)) = 51 */
		if(!(abandonCompteur() == 51)){
			throw new PostconditionError("abandonCompteur(init(l, h)) = 51 incorrecte.");
		}

		return this;
	}

	public  IMineService retrait(int s){
		/* pre retrait(M, s)) require !estLaminee(M) */
		if((estLaminee())){
			throw new PreconditionError("retrait(M, s)) require !estLaminee(M) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();
		int oldabanComp = abandonCompteur();

		// run
		super.retrait(s);

		// inv apres
		checkInvariants();

		/* post orRestant(retrait(M,s)) == orRestant(M) - s */
		if(!(orRestant() == (oldOrRest - s))){
			throw new PostconditionError("orRestant(retrait(M,s)) == orRestant(M) - s incorrecte");
		}


		/* post abandonCompteur(retrait(M,s)) = abandonCompteur(M) */
		if(!(abandonCompteur() == oldabanComp)){
			throw new PostconditionError("abandonCompteur(retrait(M,s)) = abandonCompteur(M) incorrecte.");
		}
		
		return this;

	}
	
	public IMineService accueil(){
		
		/* pre accueil(M) require !estAbandonnee(M) */
		if((!(estAbandonnee()))){
			throw new PreconditionError("accueil(M) require !estAbandonnee(M) incorrecte");
		}
	
		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.accueil();

		// inv apres
		checkInvariants();
		
		/*post orRestant(accueil(M,s)) == orRestant(M) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(accueil(M,s)) == orRestant(M) incorrecte");
		}
		
		/*post abandonCompteur(accueil(M,s)) = 0 */
		if(!(abandonCompteur() == 0)){
			throw new PostconditionError("abandonCompteur(accueil(M,s)) = 0 incorrecte");
		}
		
		return this;
	}

public IMineService abandoned(){
		
		/* pre abandoned(M) require !estAbandonee(M) */
		if(((estAbandonnee()))){
			throw new PreconditionError("abandoned(M) require estAbandonee(M) incorrecte");
		}
	
		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();
		int oldabanComp = abandonCompteur();
		
		// run
		super.abandoned();

		// inv apres
		checkInvariants();
		
		/*post orRestant(abandoned(M,s)) == orRestant(M) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(abandoned(M,s)) == orRestant(M) incorrecte");
		}
		
		/*post abandonCompteur(abandoned(M,s)) = abandonCompteur(M) + 1 */
		if(!(abandonCompteur() == oldabanComp + 1)){
			throw new PostconditionError("abandonCompteur(abandoned(M,s)) = abandonCompteur(M) + 1 incorrecte");
		}
		
		return this;
	}
}
