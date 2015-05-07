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
	
	@Override
	public IHotelVilleService setOrRestant(int s) {
		return delegate.setOrRestant(s);
	}

	@Override
	public boolean estAbandonnee() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int abandonCompteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ERace appartenance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelVilleService accueil(ERace r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelVilleService abandoned() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelVilleService setAbandonCompteur(int s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHotelVilleService setAppartenance(ERace r) {
		// TODO Auto-generated method stub
		return null;
	}
}
