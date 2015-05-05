package implementations;

import services.IHotelVilleService;
import services.IMineService;



public class HotelVilleImplem implements IHotelVilleService{
	private int largeur;
	private int	hauteur;
	private int orRestant;
	private boolean estLaminee;
	
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
	
	
	
	public boolean estLaminee(){
		return estLaminee;
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
}
