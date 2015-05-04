package decorators;

import java.util.List;

import enums.ECommande;
import enums.EResultat;
import services.IHotelVilleService;
import services.IMineService;
import services.IMoteurJeuService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;

public class MoteurJeuDecorator implements IMoteurJeuService {
	
	IMoteurJeuService delegate;
	
	
	public MoteurJeuDecorator(IMoteurJeuService delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public int LargeurTerrain() {
		// TODO Auto-generated method stub
		return delegate.LargeurTerrain();
	}

	@Override
	public int HauteurTerrain() {
		// TODO Auto-generated method stub
		return delegate.HauteurTerrain();
	}

	@Override
	public int MaxPasJeu() {
		// TODO Auto-generated method stub
		return delegate.MaxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		// TODO Auto-generated method stub
		return delegate.pasJeuCourant();
	}

	@Override
	public boolean estFini() {
		// TODO Auto-generated method stub
		return delegate.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		// TODO Auto-generated method stub
		return delegate.resultatFinal();
	}

	@Override
	public List<IVillageoisService> numerosVillageois() {
		// TODO Auto-generated method stub
		return delegate.numerosVillageois();
	}

	@Override
	public IVillageoisService getVillageois(int n) {
		// TODO Auto-generated method stub
		return delegate.getVillageois(n);
	}

	@Override
	public int positionVillageoisX(int n) {
		// TODO Auto-generated method stub
		return delegate.positionVillageoisX(n);
	}

	@Override
	public int positionVillageoisY(int n) {
		// TODO Auto-generated method stub
		return delegate.positionVillageoisY(n);
	}

	@Override
	public List<IMineService> numerosMine() {
		// TODO Auto-generated method stub
		return delegate.numerosMine();
	}

	@Override
	public IMineService getMine(int n) {
		// TODO Auto-generated method stub
		return delegate.getMine(n);
	}

	@Override
	public int positionMineX(int n) {
		// TODO Auto-generated method stub
		return delegate.positionMineX(n);
	}

	@Override
	public int positionMineY(int n) {
		// TODO Auto-generated method stub
		return delegate.positionMineY(n);
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		return delegate.peutEntrer(numVillageois, numMine);
	}

	@Override
	public IHotelVilleService HotelDeVille() {
		// TODO Auto-generated method stub
		return delegate.HotelDeVille();
	}

	@Override
	public int positionHotelVilleX() {
		// TODO Auto-generated method stub
		return delegate.positionHotelVilleX();
	}

	@Override
	public int positionHotelVilleY() {
		// TODO Auto-generated method stub
		return delegate.positionHotelVilleY();
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois) {
		// TODO Auto-generated method stub
		return delegate.peutEntrerHotelVille(numVillageois);
	}

	@Override
	public List<IRouteService> numerosRoute() {
		// TODO Auto-generated method stub
		return delegate.numerosRoute();
	}

	@Override
	public IRouteService getRoute(int n) {
		// TODO Auto-generated method stub
		return delegate.getRoute(n);
	}

	@Override
	public int positionRouteX(int n) {
		// TODO Auto-generated method stub
		return delegate.positionRouteX(n);
	}

	@Override
	public int positionRouteY(int n) {
		// TODO Auto-generated method stub
		return delegate.positionRouteY(n);
	}

	@Override
	public boolean estSurRoute(int numVillageois, int numRoute) {
		// TODO Auto-generated method stub
		return delegate.estSurRoute(numVillageois, numRoute);
	}

	@Override
	public List<IMurailleService> numerosMuraille() {
		// TODO Auto-generated method stub
		return delegate.numerosMuraille();
	}

	@Override
	public IMurailleService getMuraille(int n) {
		// TODO Auto-generated method stub
		return delegate.getMuraille(n);
	}

	@Override
	public int positionMurailleX(int n) {
		// TODO Auto-generated method stub
		return delegate.positionMurailleX(n);
	}

	@Override
	public int positionMurailleY(int n) {
		// TODO Auto-generated method stub
		return delegate.positionMurailleY(n);
	}

	@Override
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		return delegate.init(maxPasJeu, l, h);
	}

	@Override
	public IMoteurJeuService pasJeu(ECommande Commande, int numVillageois,
			int argument) {
		// TODO Auto-generated method stub
		return delegate.pasJeu(Commande, numVillageois, argument);
	}

}
