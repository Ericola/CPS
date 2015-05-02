package services;

public interface IHotelVilleService {

	/**
	 * Observators
	 */
	 
	 int getLargeur();
	 int getHauteur();
	 
	 int orRestant();
	 boolean estLaminee();
	 
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
		 * 		depot(M, s)) require !estLaminee(M)
		 * post 
				orRestant(depot(M,s)) == orRestant(M) + s
	*/
	 IHotelVilleService depot(int s);
	 
	 /**
	     * Invariants
	     */
	 
	/* estLaminee(M) =(min) orRestant(M) <=  0 */
	
}