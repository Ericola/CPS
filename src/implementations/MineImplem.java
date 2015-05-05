package implementations;

import services.IMineService;
import decorators.MineDecorator;

public class MineImplem implements IMineService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estAbandonnee;
	private boolean estLaminee;
	private int abandonCompteur;
	
	public MineImplem(){}
	
	public int hauteur(){
		return hauteur;
	}
	
	public int largeur(){
		return largeur;
	}
	
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

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IMineService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 51;
		this.abandonCompteur = 51;
		return this;
	}

	@Override
	public IMineService retrait(int s) {
		// TODO Auto-generated method stub
		this.orRestant = this.orRestant - s;
		return this;
	}

	@Override
	public IMineService accueil() {
		// TODO Auto-generated method stub
		this.estAbandonnee = false;
		this.abandonCompteur = 0;
		return this;
	}

	@Override
	public IMineService abandoned() {
		// TODO Auto-generated method stub
		this.estAbandonnee = true;
		return this;
	}
	

	@Override
	public IMineService setOrRestant(int s) {
		this.orRestant = s;
		return this;
	}
}
