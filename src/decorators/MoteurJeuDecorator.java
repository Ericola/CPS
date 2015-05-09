package decorators;

import java.awt.Point;
import java.util.HashMap;
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
	public int positionVillageoisX(IVillageoisService v) {
		// TODO Auto-generated method stub
		return delegate.positionVillageoisX(v);
	}

	@Override
	public int positionVillageoisY(IVillageoisService v) {
		// TODO Auto-generated method stub
		return delegate.positionVillageoisY(v);
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
	public int positionMineX(IMineService m) {
		// TODO Auto-generated method stub
		return delegate.positionMineX(m);
	}

	@Override
	public int positionMineY(IMineService m) {
		// TODO Auto-generated method stub
		return delegate.positionMineY(m);
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		return delegate.peutEntrer(numVillageois, numMine);
	}

	@Override
	public IHotelVilleService HotelDeVille(int n) {
		// TODO Auto-generated method stub
		return delegate.HotelDeVille(n);
	}
	
	@Override
	public int positionHotelVilleX(int n) {
		// TODO Auto-generated method stub
		return delegate.positionHotelVilleX(n);
	}

	@Override
	public int positionHotelVilleY(int n) {
		// TODO Auto-generated method stub
		return delegate.positionHotelVilleY(n);
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int hv) {
		// TODO Auto-generated method stub
		return delegate.peutEntrerHotelVille(numVillageois, hv);
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
	public int positionRouteX(IRouteService r) {
		// TODO Auto-generated method stub
		return delegate.positionRouteX(r);
	}

	@Override
	public int positionRouteY(IRouteService r) {
		// TODO Auto-generated method stub
		return delegate.positionRouteY(r);
	}

	@Override
	public boolean estSurRoute(int numVillageois) {
		// TODO Auto-generated method stub
		return delegate.estSurRoute(numVillageois);
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
	public int positionMurailleX(IMurailleService m) {
		// TODO Auto-generated method stub
		return delegate.positionMurailleX(m);
	}

	@Override
	public int positionMurailleY(IMurailleService m) {
		// TODO Auto-generated method stub
		return delegate.positionMurailleY(m);
	}
	
	 public boolean estSurMuraille(Point p){
		 return delegate.estSurMuraille(p);
	 }

	@Override
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		return delegate.init(maxPasJeu, l, h);
	}

	@Override
	public IMoteurJeuService pasJeu(ECommande Commande, ECommande Commande2, int numVillageois, 
			 int numVillageois2, int argument, int argument2) {
		// TODO Auto-generated method stub
		return delegate.pasJeu(Commande, Commande2, numVillageois, numVillageois2, argument, argument2);
	}

	@Override
	public List<Integer> VillageoisAttente() {
		// TODO Auto-generated method stub
		return delegate.VillageoisAttente();
	}

	@Override
	public List<Integer> MineMinee() {
		// TODO Auto-generated method stub
		return delegate.MineMinee();
	}

	@Override
	public HashMap<IVillageoisService, Point> positionsVillageois() {
		// TODO Auto-generated method stub
		return delegate.positionsVillageois();
	}

	@Override
	public HashMap<Object, Point> positions() {
		// TODO Auto-generated method stub
		return delegate.positions();
	}

	@Override
	public void bindHotelVille(Point positionHotelVille1,
			Point positionHotelVille2) {
		delegate.bindHotelVille(positionHotelVille1, positionHotelVille2);
	}

	@Override
	public void bindMine(List<IMineService> mines, List<Point> positionsMines) {
		delegate.bindMine(mines, positionsMines);	
	}

	@Override
	public void bindRoute(List<IRouteService> routes,
			List<Point> positionsRoutes) {
		delegate.bindRoute(routes, positionsRoutes);
	}

	@Override
	public void bindVillageois(List<IVillageoisService> villageois,
			List<Point> positionsVillageois) {
		delegate.bindVillageois(villageois, positionsVillageois);
	}

	@Override
	public void bindMuraille(List<IMurailleService> murailles,
			List<Point> positionsMurailles) {
		delegate.bindMuraille(murailles, positionsMurailles);
	}

}
