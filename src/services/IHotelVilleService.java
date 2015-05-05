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
		 * 		depot(H, s)) require !estLaminee(H)
		 * post 
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
	 /**
	     * Invariants
	     */
	 
	/* estLaminee(H) =(min) orRestant(H) <=  0 */
	
}