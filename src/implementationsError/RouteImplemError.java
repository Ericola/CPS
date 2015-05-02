package implementationsError;

import services.IRouteService;

public class RouteImplemError implements IRouteService{
	private int largeur;
	private int	hauteur;

	
	public RouteImplemError(){}
	
	public IRouteService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		this.largeur = largeur +1000;
		this.hauteur = hauteur-1000;
		return this;
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return largeur;
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return hauteur;
	}

	
}
