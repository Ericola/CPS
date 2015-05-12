package contracts;

import decorators.HotelVilleDecorator;
import decorators.MineDecorator;
import services.IHotelVilleService;
import services.IMineService;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class HotelVilleContract extends HotelVilleDecorator{
	public HotelVilleContract(IHotelVilleService delegate) {
		super(delegate);
	}

	private void checkInvariants() {
		/* estAbandonnee(H) =(min) abandonCompteur(H) = 51 && =(min) appartenance(H) = RIEN */
		if(!(estAbandonnee() == (abandonCompteur() == 51) && estAbandonnee() == (appartenance() == ERace.RIEN))){
			throw new InvariantError("estAbandonnee(H) =(min) abandonCompteur(H) = 51 && =(min) appartenance(H) = RIEN incorrecte");
		}

		/* 0 <= abandonCompteur(H) <= 51 */
		if(!(abandonCompteur() >= 0 && abandonCompteur() <= 51 )){
			throw new InvariantError("0 <= abandonCompteur(H) <= 51 incorrecte");
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

	public int abandonCompteur(){
		// aucun pre 

		return super.abandonCompteur();
	}

	public ERace appartenance() {
		// TODO Auto-generated method stub
		return super.appartenance();
	}

	public IHotelVilleService init(int largeur, int hauteur, ERace r){

		// pre init(l, h, r) require largeur > 0
		if(!( largeur > 0)){
			throw new PreconditionError(" init(l, h, r) require largeur > 0.");
		}

		// pre init(l, h, r) require hauteur > 0
		if(!(hauteur > 0)){
			throw new PreconditionError("init(l, h, r) require hauteur > 0.");
		}

		// inv avant 
		checkInvariants();

		// run
		super.init(largeur, hauteur, r);

		// inv apres
		checkInvariants();

		/* post getLargeur(init(l, h, r)) = l */
		if(!(getLargeur() == largeur)){
			throw new PostconditionError("getLargeur(init(l, h, r)) = l incorrecte.");
		}

		/* post getHauteur(init(l, h, r)) = h */
		if(!(getHauteur() == hauteur)){
			throw new PostconditionError("getHauteur(init(l, h, r)) = h incorrecte.");
		}

		/* post orRestant(init(l, h, r)) = 16 */
		if(!(orRestant() == 16)){
			throw new PostconditionError("orRestant(init(l, h, r)) = 51 incorrecte.");
		}

		/* post appartenance(init(l, h, r)) = r */
		if(!(appartenance() == r)){
			throw new PostconditionError("appartenance(init(l, h, r)) = r incorrecte.");
		}
		return this;
	}

	public  IHotelVilleService depot(int s){

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
		ERace oldAppartenance = appartenance();
		
		// inv avant 
		checkInvariants();

		// run
		super.setOrRestant(s);

		// inv apres
		checkInvariants();

		// post appartenance(setOrRestant(H, s)) = appartenance(H) */
		if (!(appartenance() == oldAppartenance)) {
			throw new PostconditionError("appartenance(setOrRestant(H, s)) = appartenance(H) incorrecte");
		}

		//post orRestant(setOrRestant(H, s)) = s
		if (!(orRestant() == s)) {
			throw new PostconditionError("orRestant(setOrRestant(H, s)) = s incorrecte");
		}	

		return this;
	}

	public IHotelVilleService accueil(ERace r){

		/* pre accueil(H) require !estAbandonnee(H) */
		if((!(estAbandonnee()))){
			throw new PreconditionError("accueil(H, r) require !estAbandonnee(H) incorrecte");
		}

		/* pre accueil(H, r) require r != RIEN */
		if((!(r == ERace.RIEN))){
			throw new PreconditionError("accueil(H, r) require r != RIEN incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.accueil(r);

		// inv apres
		checkInvariants();

		/*post orRestant(accueil(H,r)) == orRestant(H) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(accueil(H,r)) == orRestant(H) incorrecte");
		}

		/*post abandonCompteur(accueil(H,r)) = 0 */
		if(!(abandonCompteur() == 0)){
			throw new PostconditionError("abandonCompteur(accueil(H,r)) = 0 incorrecte");
		}

		/*post 	appartenance(accueil(H,r)) = r */
		if(!(appartenance() == r)){
			throw new PostconditionError("appartenance(accueil(H,r)) = r incorrecte");
		}
		return this;
	}

	public IHotelVilleService abandoned(){

		/* pre abandoned(H) require !estAbandonee(H) */
		if(((estAbandonnee()))){
			throw new PreconditionError("abandoned(H) require estAbandonee(H) incorrecte");
		}

		// inv avant 
		checkInvariants();

		// capture 
		int oldOrRest = orRestant();

		// run
		super.abandoned();

		// inv apres
		checkInvariants();

		/*post orRestant(abandoned(H,s)) == orRestant(H) */
		if(!(orRestant() == oldOrRest)){
			throw new PostconditionError("orRestant(abandoned(H,s)) == orRestant(H) incorrecte");
		}

		/*post appartenance(accueil(H,r)) = RIEN */
		if(!(appartenance() == ERace.RIEN)){
			throw new PostconditionError("appartenance(accueil(H,r)) = RIEN incorrecte");
		}

		return this;
	}

	public IHotelVilleService setAbandonCompteur(int s){
		// pre setAbandonCompteur(H, s) require s > 0
		if (!(s > 0)) {
			throw new PreconditionError("setAbandonCompteur require s > 0 incorrecte");
		}

		// capture 
		int oldOrRestant = orRestant();

		// inv avant 
		checkInvariants();

		// run
		super.setAbandonCompteur(s);

		// inv apres
		checkInvariants();

		//post abandonCompteur(setabandonCompteur(H, s)) = abandonCompteur(H)
		if (!(orRestant() == oldOrRestant)) {
			throw new PostconditionError("orRestant(setAbandonCompteur(H, s)) = abandonCompteur(H) incorrecte");
		}	

		//post abandonCompteur(setAbandonCompteur(H, s)) = s
		if (!(abandonCompteur() == s)) {
			throw new PostconditionError("abandonCompteur(setAbandonCompteur(H, s)) = s incorrecte");
		}	

		return this;
	}


	@Override
	public IHotelVilleService setAppartenance(ERace r) {

		// capture 
		int oldOrRestant = orRestant();

		// inv avant 
		checkInvariants();

		// run
		super.setAppartenance(r);

		// inv apres
		checkInvariants();

		//post appartenance(setAppartenance(H, r)) = r
		if (!(appartenance() == r)) {
			throw new PostconditionError("appartenance(setAppartenance(H, r)) = r incorrecte");
		}	

		//post orRestant(setAppartenance(H, r)) = orRestant(H)
		if (!(orRestant() == oldOrRestant)) {
			throw new PostconditionError("orRestant(setAppartenance(H, r)) = orRestant(H) incorrecte");
		}

		return this;
	}
}
