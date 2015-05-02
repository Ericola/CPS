package decorators;

import enums.ERace;
import services.IMurailleService;
import services.IVillageoisService;

public class MurailleDecorator implements IMurailleService{
	private IMurailleService delegate;
	
	public  MurailleDecorator(IMurailleService delegate) {
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
	public int getPdv() {
		// TODO Auto-generated method stub
		return delegate.getPdv();
	}
	
	@Override
	public boolean estMort() {
		// TODO Auto-generated method stub
		return delegate.estMort();
	}
	
	@Override
	public IMurailleService init(int largeur, int hauteur, int pdv) {
		// TODO Auto-generated method stub
		return delegate.init(largeur, hauteur, pdv);
	}
	
	@Override
	public IMurailleService retrait(int s) {
		// TODO Auto-generated method stub
		return delegate.retrait(s);
	}
}
