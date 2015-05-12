package services;

public interface IRouteService {
	/**
	 * Observators
	 */
	 
	 int getLargeur();
	 int getHauteur();
	 
	 /**
		 * Constructors
		 */
	 /*
		 * pre 
		 * 		init(l,h) require l > 0 ^ h > 0
		 *                                                
		 * post 
				getLargeur(init(l, h)) = l
				getHauteur(init(l, h)) = h
			
					
		 */
	 IRouteService init(int largeur, int hauteur);
}
