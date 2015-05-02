package services;

public interface IMurailleService {
	/**
	 * Observators
	 */
	 
	 int getLargeur();
	 int getHauteur();
	 int getPdv();
	 boolean estMort();
	 
	 /**
		 * Constructors
		 */
	 /*
		 * pre 
		 * 		init(l,h, pdv) require l % 2 = 1 ^ h % 2 = 1 ^ pdv > 0
		 *                                                
		 * post 
				getLargeur(init(l, h)) = l
				getHauteur(init(l, h)) = h
				getPdv(init(l, h, pdv) = pdv
		 */
	 
	 IMurailleService init(int largeur, int hauteur, int pdv);
	 
	 /**
		 * Operators
		 */
	 /*
		 * pre 
		 * 		getpdv(retrait(V, s)) require s > 0 AND !estMort(V)
		 * post 
				getpdv(retrait(V, s)) =getpdv(V)-s
				getQtor(retrait(V)) = getQtor(V)
		
				
		 */
	 IMurailleService retrait(int s);
	 
	 /**
	     * Invariants
	     */
	 
	/* estMort(V) =(min) getpdv(V) <= 0 */
}
