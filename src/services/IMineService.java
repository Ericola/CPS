package services;

import enums.ERace;

public interface IMineService {
	/**
	 * Observators
	 */
	 
	 int getLargeur();
	 int getHauteur();
	 
	 int orRestant();
	 boolean estAbandonnee();
	 boolean estLaminee();
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
				abandonCompteur(init(l, h)) = 51
					
		 */
	 IMineService init(int largeur, int hauteur);
	 
	 /**
		 * Operators
		 */
	 /*
		 * pre 
		 * 		retrait(M, s)) require !estLaminee(M)
		 * post 
				orRestant(retrait(M,s)) == orRestant(M) - s
				abandonCompteur(retrait(M,s)) = abandonCompteur(M)
	*/
	 IMineService retrait(int s);
	 
	 /*
		 * pre 
		 * 		accueil(M) require !estAbandonee(M) && r != RIEN
		 * post 
				orRestant(accueil(M,s)) == orRestant(M)
				abandonCompteur(accueil(M,r)) = 0
	*/
	 IMineService accueil(ERace r);
	  
	 /*
		 * pre 
		 * 		abandoned(M) require estAbandonee(M)
		 * post 
				orRestant(abandoned(M,s)) == orRestant(M)
	*/
	 IMineService abandoned();
	 
	/* pre 
	 * 		setOrRestant(M, s) require s > 0
	 * post
			orRestant(setOrRestant(M, s)) = orRestant(M)
			orRestant(setOrRestant(M, s)) = s
				
		 */
	 IMineService setOrRestant(int s);
	 /* pre 
		 * 		setAbandonCompteur(M, s) require s > 0
		 * post
				orRestant(setAbandonCompteur(M, s)) = AbandonCompteur(M)
				orRestant(setAbandonCompteur(M, s)) = s
					
			 */
	 IMineService setAbandonCompteur(int s);
	 

	 /* post
				appartenance(setAppartenance(M, r)) = r 
					
			 */
	 IMineService setAppartenance(ERace r);
     /**
	     * Invariants
	     */
	 
	/* estLaminee(M) =(min) orRestant(M) <=  0 */
	/* estAbandonnee(M) =(min) abandonCompteur(M) = 51 */
	/* 0 <= abandonCompteur(M) <= 51 */ 
}


