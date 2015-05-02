package implementations;

import services.IRouteService;

public class RouteImplem implements IRouteService{
	private int largeur;
	private int	hauteur;

	
	public RouteImplem(){}
	
	
	
	public IRouteService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		this.largeur = largeur;
		this.hauteur = hauteur;
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
