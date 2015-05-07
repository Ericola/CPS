package implementations;

import enums.ERace;
import services.IHotelVilleService;
import services.IMineService;



public class HotelVilleImplem implements IHotelVilleService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estAbandonnee;
	private int abandonCompteur;
	private ERace appartenance;
	
	public HotelVilleImplem(){}
	
	public int hauteur(){
		return hauteur;
	}
	
	public int largeur(){
		return largeur;
	}
	
	public int orRestant(){
		return orRestant;
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
	public IHotelVilleService init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.orRestant = 51;
		return this;
	}

	@Override
	public IHotelVilleService depot(int s) {
		// TODO Auto-generated method stub
		this.orRestant = this.orRestant + s;
		return this;
	}

	@Override
	public IHotelVilleService setOrRestant(int s) {
		this.orRestant = s;
		return this;
	}

	@Override
	public boolean estAbandonnee() {
		// TODO Auto-generated method stub
		return estAbandonnee;
	}

	@Override
	public int abandonCompteur() {
		// TODO Auto-generated method stub
		return abandonCompteur;
	}

	@Override
	public ERace appartenance() {
		// TODO Auto-generated method stub
		return appartenance;
	}

	@Override
	public IHotelVilleService accueil(ERace r) {
		this.estAbandonnee = false;
		this.abandonCompteur = 0;
		this.appartenance = r;
		return this;	
	}

	@Override
	public IHotelVilleService abandoned() {
		// TODO Auto-generated method stub
		this.estAbandonnee = true;
		this.appartenance = ERace.RIEN;
		return this;
	}

	@Override
	public IHotelVilleService setAbandonCompteur(int s) {
		this.abandonCompteur = s;
		return this;
	}

	@Override
	public IHotelVilleService setAppartenance(ERace r) {
		this.appartenance = r;
		return this;
	}
}
