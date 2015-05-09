package implementationsError;

import services.IMineService;
import services.IVillageoisService;
import enums.ERace;

public class MineImplemError implements IMineService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estAbandonnee;
	private boolean estLaminee;
	private int abandonCompteur;
	private ERace appartenance;
	
	public MineImplemError(){}
	
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
	
	public ERace appartenance(){
		return appartenance;
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
		this.largeur = largeur + 100;
		this.hauteur = hauteur + 100;
		this.orRestant = 5100;
		this.abandonCompteur = 5100;
		return this;
	}

	@Override
	public IMineService retrait(int s) {
		// TODO Auto-generated method stub
		this.orRestant = this.orRestant - s - 42;
		return this;
	}

	@Override
	public IMineService accueil(ERace r) {
		// TODO Auto-generated method stub
		this.estAbandonnee = true;
		this.abandonCompteur = 10;
		return this;
	}

	@Override
	public IMineService abandoned() {
		// TODO Auto-generated method stub
		this.estAbandonnee = false;
		return this;
	}
	
	@Override
	public IMineService setOrRestant(int s) {
		this.orRestant = s + 50;
		return this;
	}
	
	public IMineService setAbandonCompteur(int s){
		this.abandonCompteur = s + 50;
		return this;
	}
	

	@Override
	public IMineService setAppartenance(ERace r) {
		this.appartenance = r;
		return this;
	}
	
	@Override
	public IMineService setLaminee() {
		this.estLaminee = true;
		return this;
	}
}
