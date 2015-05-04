package implementations;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
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
		
		return largeurTerrain;
	}

	@Override
	public int HauteurTerrain() {
		// TODO Auto-generated method stub
		return hauteurTerrain;
	}

	@Override
	public int MaxPasJeu() {
		// TODO Auto-generated method stub
		return maxPasJeu;
	}

	@Override
	public int pasJeuCourant() {
		// TODO Auto-generated method stub
		return pasJeuCourant;
	}

	@Override
	public boolean estFini() {
		// TODO Auto-generated method stub
		return estFini;
	}

	@Override
	public EResultat resultatFinal() {
		// TODO Auto-generated method stub
		return resultatFinal;
	}

	@Override
	public List<IVillageoisService> numerosVillageois() {
		// TODO Auto-generated method stub
		return villageois;
		
	}

	@Override
	public IVillageoisService getVillageois(int n) {
		// TODO Auto-generated method stub
		return villageois.get(n);
	}

	@Override
	public int positionVillageoisX(int n) {
		// TODO Auto-generated method stub
		return positions.get(villageois.get(n)).x;
	}

	@Override
	public int positionVillageoisY(int n) {
		// TODO Auto-generated method stub
		return  positions.get(villageois.get(n)).y;
	}

	@Override
	public List<IMineService> numerosMine() {
		// TODO Auto-generated method stub
		return mines;
	}

	@Override
	public IMineService getMine(int n) {
		// TODO Auto-generated method stub
		return mines.get(n);
	}

	@Override
	public int positionMineX(int n) {
		// TODO Auto-generated method stub
		return positions.get(mines.get(n)).x;
	}

	@Override
	public int positionMineY(int n) {
		// TODO Auto-generated method stub
		return positions.get(mines.get(n)).y;
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		double distance=positions.get(mines.get(numMine)).distance(positions.get(villageois.get(numVillageois)));
		return distance<=51;
	}

	@Override
	public IHotelVilleService HotelDeVille() {
		// TODO Auto-generated method stub
		return hotelDeVille;
	}

	@Override
	public int positionHotelVilleX() {
		// TODO Auto-generated method stub
		return positions.get(hotelDeVille).x;
	}

	@Override
	public int positionHotelVilleY() {
		// TODO Auto-generated method stub
		return positions.get(hotelDeVille).y;
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois) {
		// TODO Auto-generated method stub
		double distance=positions.get(hotelDeVille).distance(positions.get(hotelDeVille));
		return distance<=51;
	}

	@Override
	public List<IRouteService> numerosRoute() {
		// TODO Auto-generated method stub
		return routes;
	}

	@Override
	public IRouteService getRoute(int n) {
		// TODO Auto-generated method stub
		return routes.get(n);
	}

	@Override
	public int positionRouteX(int n) {
		// TODO Auto-generated method stub
		return positions.get(routes.get(n)).x;
	}

	@Override
	public int positionRouteY(int n) {
		// TODO Auto-generated method stub
		return positions.get(routes.get(n)).y;
	}

	@Override
	public boolean estSurRoute(int numVillageois, int numRoute) {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(positionRouteX(numRoute), positionRouteY(numRoute), routes.get(numRoute).getLargeur(), routes.get(numRoute).getHauteur());
		return rect.contains(positions.get(getVillageois(numVillageois)));
	}

	@Override
	public List<IMurailleService> numerosMuraille() {
		// TODO Auto-generated method stub
		return murailles;
	}

	@Override
	public IMurailleService getMuraille(int n) {
		// TODO Auto-generated method stub
		return murailles.get(n);
	}

	@Override
	public int positionMurailleX(int n) {
		// TODO Auto-generated method stub
		return  positions.get(murailles.get(n)).x;
	}

	@Override
	public int positionMurailleY(int n) {
		// TODO Auto-generated method stub
		return positions.get(murailles.get(n)).y;
	}

	@Override
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		villageois= new ArrayList<IVillageoisService>();
		 mines=new ArrayList<IMineService>();
		// hotelDeVille=new IHotelVilleService();
		routes=new ArrayList<IRouteService>();
		murailles=new ArrayList<IMurailleService>();
		
		 this.maxPasJeu=maxPasJeu;
		 pasJeuCourant=0;
		
		 
		 largeurTerrain = l;
		 hauteurTerrain = h;
		 
		 estFini = false;
		 
		  positions =new HashMap<Object, Point>();
		  
		return this;
		
	}


	
	public Point deplacer(int argument,Point point){
		if(argument<46){
			return new Point(point.x+1,point.y-1);
		}
		if(argument<91){
			return new Point(point.x,point.y-1);
		}
		if(argument<136){
			return new Point(point.x-1,point.y-1);
		}
		if(argument<181){
			return new Point(point.x-1,point.y);
		}
		if(argument<226){
			return new Point(point.x-1,point.y+1);
		}
		if(argument<271){
			return new Point(point.x,point.y+1);
		}
		if(argument<316){
			return new Point(point.x+1,point.y+1);
		}
		if(argument<361){
			return new Point(point.x+1,point.y);
		}
		
		
		
		return point;
	}
	
	@Override
	public IMoteurJeuService pasJeu(ECommande Commande, int numVillageois,
			int argument) {
		// TODO Auto-generated method stub
		
		switch(Commande){
		case DEPLACER : 
			Point yo=deplacer(argument,positions.get(villageois.get(numVillageois)));
			positions.put(villageois.get(numVillageois), yo);
			break;
			
		case ENTRERMINE : break;
			//ajouter un boolean au villageois actif/inactif
			
		//tester si la mine si abandonné -> non abandonné
		
			//mettre le bool a inactif
			//une fois les 16pas de jeu fini le remettre actif
			//il a une piece :)
			//qtor++
			
		case ENTRERHOTELVILLE : break;
		//ajouter un boolean au villageois actif/inactif
		
		
		
		//il a plus de piece :)
		//qtor=0
		
		/*rajout de case : murailleMeet
		 * 
		 * 
		 * rajout SurRoute
		 * Multiplier par 2 la vitesse du villageois !
		 * 
		 * 
		 */
		default:
			break;
			
		
		}
		pasJeuCourant++;
		return this;
	}

}
