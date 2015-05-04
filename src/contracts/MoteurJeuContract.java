package contracts;

import services.IMoteurJeuService;
import decorators.MoteurJeuDecorator;

public class MoteurJeuContract extends MoteurJeuDecorator{

	public MoteurJeuContract(IMoteurJeuService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	
	private void checkInvariants() {
		
	}

}
