package decorators;

import services.IHotelVilleService;
import services.IMineService;

public class HotelVilleDecorator implements IHotelVilleService {
	private IHotelVilleService delegate;
	
	public HotelVilleDecorator(IHotelVilleService delegate) {
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
	public int orRestant() {
		// TODO Auto-generated method stub
		return delegate.orRestant();
	}


	@Override
	public boolean estLaminee() {
		// TODO Auto-generated method stub
		return delegate.estLaminee();
	}

	@Override
	public IHotelVilleService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return delegate.init(largeur, hauteur);
	}

	@Override
	public IHotelVilleService depot(int s) {
		// TODO Auto-generated method stub
		return delegate.depot(s);
	}
}
