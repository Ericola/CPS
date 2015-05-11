package services;

import enums.ERace;

public interface IHotelVilleService {

	/**
	 * Observators
	 */
	 
	 int getLargeur();
	 int getHauteur();
	 
	 int orRestant();
	 boolean estAbandonnee();
	 int abandonCompteur();
	 ERace appartenance();
	 
	 /**
		 * Constructors
		 */
	 /*
		 * pre 
		 * 		init(l,h, r) require l > 0 ^ h > 0
		 *                                                
		 * post 
				getLargeur(init(l, h, r)) = l
				getHauteur(init(l, h, r)) = h
				orRestant(init(l, h, r)) = 16
				appartenance(init(l, h, r)) = r
					
		 */
	 IHotelVilleService init(int largeur, int hauteur, ERace r);
	 
	 /**
		 * Operators
		 */
	 /*
		 * pre 
		 * 		accueil(H, r) require !estAbandonee(H) && r != RIEN
		 * post 
				orRestant(accueil(H,s)) == orRestant(H)
				abandonCompteur(accueil(H,r)) = 0
				appartenance(accueil(H,r)) = r 
	*/
	 IHotelVilleService accueil(ERace r);
	  
	 /*
		 * pre 
		 * 		abandoned(H) require estAbandonee(H)
		 * post 
				orRestant(abandoned(H) == orRestant(H)
				appartenance(abandoned(H)) = RIEN 
	*/
	 IHotelVilleService abandoned();
	 
	 /*
		 post 
				orRestant(depot(H,s)) == orRestant(H) + s
				appartenance(abandoned(H)) = RIEN
	*/
	 IHotelVilleService depot(int s);
	 
	 
	/* 
	     * pre 
	     * 		setOrRestant(H, s) require s > 0
		 * post
				orRestant(setOrRestant(H, s)) = orRestant(H)
				orRestant(setOrRestant(H, s)) = s
					
			 */
	IHotelVilleService setOrRestant(int s);
		 
     /* pre 
			 * 		setAbandonCompteur(H, s) require s > 0
			 * post
					orRestant(setAbandonCompteur(H, s)) = AbandonCompteur(H)
					orRestant(setAbandonCompteur(H, s)) = s
						
				 */
    IHotelVilleService setAbandonCompteur(int s);
		 

		 /* post
					appartenance(setAppartenance(H, r)) = r 
					orRestant(setAppartenance(H, r)) = orRestant(H)
						
				 */
	IHotelVilleService setAppartenance(ERace r);
	 /**
	     * Invariants
	     */
	/* estAbandonnee(H) =(min) abandonCompteur(H) = 51 && =(min) appartenance(H) = RIEN */
	/* 0 <= abandonCompteur(H) <= 51 */
	
	
}