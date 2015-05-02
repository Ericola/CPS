package decorators;

import enums.ERace;
import services.IVillageoisService;



public class VillageoisDecorator implements IVillageoisService{
private IVillageoisService delegate;
	
	public  VillageoisDecorator(IVillageoisService delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public ERace getRace() {
		// TODO Auto-generated method stub
		return delegate.getRace();
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
	public int getForce() {
		// TODO Auto-generated method stub
		return delegate.getForce();
	}

	@Override
	public int getVitesse() {
		// TODO Auto-generated method stub
		return delegate.getVitesse();
	}

	@Override
	public int getPdv() {
		// TODO Auto-generated method stub
		return delegate.getPdv();
	}

	@Override
	public int getQtor() {
		// TODO Auto-generated method stub
		return delegate.getQtor();
	}

	@Override
	public IVillageoisService init(ERace type, int largeur, int hauteur,
			int force, int vitesse, int pdv) {
		// TODO Auto-generated method stub
		return delegate.init(type, largeur, hauteur, force, vitesse, pdv);
	}

	@Override
	public IVillageoisService retrait(int s) {
		// TODO Auto-generated method stub
		return delegate.retrait(s);
	}

	@Override
	public boolean estMort() {
		// TODO Auto-generated method stub
		return delegate.estMort();
	}

}
