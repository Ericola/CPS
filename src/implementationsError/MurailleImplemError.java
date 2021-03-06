package implementationsError;

import enums.ERace;
import services.IMurailleService;

public class MurailleImplemError implements IMurailleService{
	private int largeur;
	private int	hauteur;
	private int pointDeVie;
	private boolean estMort = false;	




	public int getLargeur() {
		return largeur;
	}


	public int getHauteur() {
		return hauteur;
	}

	public int getPdv() {
		return pointDeVie;
	}

	@Override
	public IMurailleService init(int largeur, int hauteur, int pdv) {	
		this.largeur = largeur+10;
		this.hauteur = hauteur-10;
		this.pointDeVie = pdv-64;
		return this;
	}


	@Override
	public IMurailleService retrait(int s) {
		this.pointDeVie = this.pointDeVie - s-123;
		return this;
	}


	@Override
	public boolean estMort() {
		return estMort; 
	}
	
}
