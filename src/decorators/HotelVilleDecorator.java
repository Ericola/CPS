package decorators;

import enums.ERace;
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
	public IHotelVilleService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return delegate.init(largeur, hauteur);
	}

	@Override
	public IHotelVilleService depot(int s) {
		// TODO Auto-generated method stub
		return delegate.depot(s);
	}
	
	@Override
	public IHotelVilleService setOrRestant(int s) {
		return delegate.setOrRestant(s);
	}

	@Override
	public boolean estAbandonnee() {
		return delegate.estAbandonnee();
	}

	@Override
	public int abandonCompteur() {
		// TODO Auto-generated method stub
		return delegate.abandonCompteur();
	}

	@Override
	public ERace appartenance() {
		// TODO Auto-generated method stub
		return delegate.appartenance();
	}

	@Override
	public IHotelVilleService accueil(ERace r) {
		// TODO Auto-generated method stub
		return delegate.accueil(r);
	}

	@Override
	public IHotelVilleService abandoned() {
		// TODO Auto-generated method stub
		return delegate.abandoned();
	}

	@Override
	public IHotelVilleService setAbandonCompteur(int s) {
		// TODO Auto-generated method stub
		return delegate.setAbandonCompteur(s);
	}

	@Override
	public IHotelVilleService setAppartenance(ERace r) {
		// TODO Auto-generated method stub
		return delegate.setAppartenance(r);
	}
}
