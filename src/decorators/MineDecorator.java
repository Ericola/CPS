package decorators;

import services.IMineService;
import services.IVillageoisService;

public class MineDecorator implements IMineService {
	private IMineService delegate;
	
	public MineDecorator(IMineService delegate) {
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
	public boolean estAbandonnee() {
		// TODO Auto-generated method stub
		return delegate.estAbandonnee();
	}

	@Override
	public boolean estLaminee() {
		// TODO Auto-generated method stub
		return delegate.estLaminee();
	}

	@Override
	public int abandonCompteur() {
		// TODO Auto-generated method stub
		return delegate.abandonCompteur();
	}

	@Override
	public IMineService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return delegate.init(largeur, hauteur);
	}

	@Override
	public IMineService retrait(int s) {
		// TODO Auto-generated method stub
		return delegate.retrait(s);
	}

	@Override
	public IMineService accueil() {
		// TODO Auto-generated method stub
		return delegate.accueil();
	}

	@Override
	public IMineService abandoned() {
		// TODO Auto-generated method stub
		return delegate.abandoned();
	}
	

	@Override
	public IMineService setOrRestant(int s) {
		return delegate.setOrRestant(s);
	}

}
