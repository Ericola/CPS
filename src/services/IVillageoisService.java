package services;


import enums.ERace;

public interface IVillageoisService {
	/**
	 * Observators
	 */
	 ERace getRace();
	 int getLargeur();
	 int getHauteur();
	 int getForce();
	 int getVitesse();
	 int getPdv();
	 int getQtor();
	 boolean estMort();
	 
	 /**
		 * Constructors
		 */
	 /*
		 * pre 
		 * 		init(type, l, h, f, v, pdv) require l % 2 = 1 ^ h % 2 = 1
		 *                                                ^ 0 < f < pdv ^ 0 <= v
		 * post 
				getLargeur(init(type, l, h, f, v, pdv)) = l
				getHauteur(init(type, l, h, f, v, pdv)) = h
				getForce(init(type, l, h, f, v, pdv)) = f
				getVitesse(init(type, l, h, f, v, pdv)) = v
				getPdv(init(type, l, h, f, v, pdv)) = pdv
				getQtor(init(type, l, h, f, v, pdv)) = 0
				
		 */
	 IVillageoisService init(ERace type, int largeur, int hauteur,int force,int vitesse,int pdv);
	 
	 /**
		 * Operators
		 */
	 /*
		 * pre 
		 * 		getpdv(retrait(V, s)) require s > 0 AND !estMort(V)
		 * post 
				getpdv(retrait(V, s)) =getpdv(V)-s
				getQtor(retrait(V)) = getQtor(V)
				getVitesse(retrait(V, s)) = getVitesse(V) 
				
		 */
	 IVillageoisService retrait(int s);
	  
	 /*	pre 
	  * setQtor(V, s) require s > 0
	  *	post
			getQtor(setQtor(V, s)) = getQtor(V)
			getQtor(setQtor(V, s)) = s
			getVitesse(setQtor(V, s)) = getVitesse(V) 
			
				
		 */
	 IVillageoisService setQtor(int s);
     /**
	     * Invariants
	     */
	 
	/* estMort(V) =(min) getpdv(V) <= 0 */
}
