package implementationsError;

import enums.ERace;
import services.IHotelVilleService;



public class HotelVilleImplemError implements IHotelVilleService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estAbandonnee = false;
	private int abandonCompteur = 0;
	private ERace appartenance;
	
	public HotelVilleImplemError(){}

	public int orRestant(){
		return orRestant;
	}
	
	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public IHotelVilleService init(int largeur, int hauteur, ERace r) {
		// TODO Auto-generated method stub
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.appartenance = r;
		this.orRestant = 16+10;
		if(r == ERace.RIEN){
			abandonCompteur = 51;
			estAbandonnee = true;
		}
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
		this.orRestant = s + 50;
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
		this.estAbandonnee = true;
		this.abandonCompteur = 51;
		this.appartenance = ERace.RIEN;
		return this;	
	}

	@Override
	public IHotelVilleService abandoned() {
		// TODO Auto-generated method stub
		this.estAbandonnee = true;
		this.appartenance = ERace.HUMAIN;
		this.abandonCompteur = 51;
		return this;
	}

	@Override
	public IHotelVilleService setAbandonCompteur(int s) {
		this.abandonCompteur = s + 50;
		return this;
	}

	@Override
	public IHotelVilleService setAppartenance(ERace r) {
		this.appartenance = ERace.RIEN;
		if(r != ERace.RIEN){
			this.abandonCompteur = 0;
			estAbandonnee = false;
		}
		return this;
	}
}
