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
		 * 		init(l,h) require l % 2 = 1 ^ h % 2 = 1
		 *                                                
		 * post 
				getLargeur(init(l, h)) = l
				getHauteur(init(l, h)) = h
			
					
		 */
	 IRouteService init(int largeur, int hauteur);
}
