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
		 * 		init(l,h) require l % 2 = 1 ^ h % 2 = 1
		 *                                                
		 * post 
				getLargeur(init(l, h)) = l
				getHauteur(init(l, h)) = h
				orRestant(init(l, h)) = 51
					
		 */
	 IHotelVilleService init(int largeur, int hauteur);
	 
	 /**
		 * Operators
		 */
	 /*
		 * pre 
		 * 		accueil(H) require !estAbandonee(H)
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
					appartenance(setAppartenance(M, r)) = r 
						
				 */
	IHotelVilleService setAppartenance(ERace r);
	 /**
	     * Invariants
	     */
	/* estAbandonnee(M) =(min) abandonCompteur(M) = 51 && =(min) appartenance(M) = RIEN */
	/* 0 <= abandonCompteur(M) <= 51 */
	
	
}