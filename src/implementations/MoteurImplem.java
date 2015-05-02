package implementations;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;
import enums.ECommande;
import enums.EResultat;

public class MoteurImplem implements IMoteurJeuService {
	private int maxPasJeu;
	private int pasJeuCourant;
	private int largeurTerrain;
	private int hauteurTerrain;
	
	private boolean estFini;
	private EResultat resultatFinal;
	
	private List<IVillageoisService> villageois;
	private List<IMineService> mines;
	private IHotelVilleService hotelDeVille;
	private List<IRouteService> routes;
	private List<IMurailleService> murailles;
	
	private HashMap<Object, Point> positions;
	@Override
	public int LargeurTerrain() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int HauteurTerrain() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int MaxPasJeu() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pasJeuCourant() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estFini() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EResultat resultatFinal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> numerosVillageois() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVillageoisService getVillageois(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionVillageoisX(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionVillageoisY(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Integer> numerosMine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMineService getMine(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionMineX(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionMineY(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IHotelVilleService HotelDeVille() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionHotelVilleX(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionHotelVilleY(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Integer> numerosRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRouteService getRoute(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionRouteX(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionRouteY(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estSurRoute(int numVillageois, int numRoute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Integer> numerosMuraille() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRouteService getMuraille(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionMurailleX(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionMurailleY(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMoteurJeuService pasJeu(ECommande Commande, int numVillageois,
			int argument) {
		// TODO Auto-generated method stub
		return null;
	}

}
