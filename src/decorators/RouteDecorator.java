package decorators;

import services.IRouteService;


public class RouteDecorator implements IRouteService {
private IRouteService delegate;
	
	public RouteDecorator(IRouteService delegate) {
		super();
		this.delegate = delegate;
	}
	
	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return delegate.getLargeur();
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return delegate.getHauteur();
	}

	@Override
	public IRouteService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return delegate.init(largeur, hauteur);
	}
}
