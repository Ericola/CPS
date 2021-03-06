package implementations;

import services.IMineService;
import decorators.MineDecorator;
import enums.ERace;

public class MineImplem implements IMineService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estAbandonnee;
	private boolean estLaminee;
	private int abandonCompteur;
	private ERace appartenance;

	public MineImplem(){}

	public int orRestant(){
		return orRestant;
	}

	public boolean estAbandonnee(){
		return estAbandonnee;
	}

	public boolean estLaminee(){
		return estLaminee;
	}

	public int abandonCompteur(){
		return abandonCompteur;
	}

	public ERace appartenance(){
		return appartenance;
	}

	@Override
	public int getLargeur() {
		return hauteur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public IMineService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 51;
		this.abandonCompteur = 51;
		this.appartenance = ERace.RIEN;
		this.estAbandonnee = true;
		return this;
	}

	@Override
	public IMineService retrait(int s) {
		// TODO Auto-generated method stub
		this.orRestant = this.orRestant - s;
		if(this.orRestant == 0){
			estLaminee = true;
		}
		return this;
	}

	@Override
	public IMineService accueil(ERace r) {
		// TODO Auto-generated method stub
		this.estAbandonnee = false;
		this.abandonCompteur = 0;
		this.appartenance = r;
		return this;
	}

	@Override
	public IMineService abandoned() {
		// TODO Auto-generated method stub
		this.estAbandonnee = true;
		this.appartenance = ERace.RIEN;
		return this;
	}

	public IMineService setAbandonCompteur(int s){
		this.abandonCompteur = s;
		return this;
	}


	@Override
	public IMineService setOrRestant(int s) {
		this.orRestant = s;
		return this;
	}

	@Override
	public IMineService setAppartenance(ERace r) {
		this.appartenance = r;
		if(r != ERace.RIEN){
			this.abandonCompteur = 0;
			estAbandonnee = false;
		}
		return this;
	}

	@Override
	public IMineService setLaminee() {
		this.estLaminee = true;
		return this;
	}

}
