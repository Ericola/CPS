package implementations;

import enums.ERace;
import services.IVillageoisService;

public class VillageoisImplem implements IVillageoisService{
	private ERace type;
	private int largeur;
	private int	hauteur;
	private int force;
	private int vitesse;
	private int pointDeVie;
	private int quantiteDor;
	private boolean estMort = false;	

	public ERace getRace() {
		return type;
	}


	public int getLargeur() {
		return largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public int getForce() {
		return force;
	}


	public int getVitesse() {
		return vitesse;
	}


	public int getPdv() {
		return pointDeVie;
	}


	public int getQtor() {
		return quantiteDor;
	}


	@Override
	public IVillageoisService init(ERace type, int largeur, int hauteur,
			int force, int vitesse, int pdv) {	
		this.type = type;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.force = force;
		this.vitesse = vitesse;
		this.pointDeVie = pdv;
		this.quantiteDor = 0;
		return this;
	}


	@Override
	public IVillageoisService retrait(int s) {
		this.pointDeVie = this.pointDeVie - s;
		return this;
	}


	@Override
	public boolean estMort() {
		return estMort; 
	}


	@Override
	public IVillageoisService setQtor(int s) {
		this.quantiteDor = s;
		return this;
	}
	
	
	
	
}
