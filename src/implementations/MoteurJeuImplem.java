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
import enums.ERace;
import enums.EResultat;

public class MoteurJeuImplem implements IMoteurJeuService {
	private int maxPasJeu;
	private int pasJeuCourant;
	private int largeurTerrain;
	private int hauteurTerrain;

	private boolean estFini;
	private EResultat resultatFinal;

	private List<IVillageoisService> villageois;
	private List<IMineService> mines;
	private IHotelVilleService hotelDeVille;
	private IHotelVilleService hotelDeVille2;
	private List<IRouteService> routes;
	private List<IMurailleService> murailles;

	private List<Integer> VillageoisAttente;
	private List<Integer> MineMinee;
	private HashMap<Object, Point> positions;
	private HashMap<IVillageoisService, Point> positionsVillageois;
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
	public int positionVillageoisX(IVillageoisService v) {
		// TODO Auto-generated method stub
		return positionsVillageois.get(v).x;
	}

	@Override
	public int positionVillageoisY(IVillageoisService v) {
		// TODO Auto-generated method stub
		return  positionsVillageois.get(v).y;
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
	public int positionMineX(IMineService m) {
		// TODO Auto-generated method stub
		return positions.get(m).x;
	}

	@Override
	public int positionMineY(IMineService m) {
		// TODO Auto-generated method stub
		return positions.get(m).y;
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO Auto-generated method stub
		if(getMine(numMine).estLaminee() 
				||(getMine(numMine).appartenance() != ERace.RIEN && getMine(numMine).appartenance() != getVillageois(numVillageois).getRace())){
			return false;
		}
		
		double distance=positions.get(getMine(numMine)).distance(positions.get(villageois.get(numVillageois)));
		if(getMine(numMine).estAbandonnee()){
			return distance < 51;
		}
		int nbVillageoisDansMine = 0;
		for(int i = 0; i < MineMinee.size(); i++){
			if(MineMinee.get(i) == numMine){
				nbVillageoisDansMine++;
			}
		}
		
		return getMine(numMine).orRestant() - nbVillageoisDansMine > 0 && distance < 51;
		
	}

	@Override
	public IHotelVilleService HotelDeVille(int n) {
		// TODO Auto-generated method stub
		if(n == 1)
			return hotelDeVille;
		return hotelDeVille2;
	}

	@Override
	public int positionHotelVilleX(int n) {
		// TODO Auto-generated method stub
		if(n == 1)
			return positions.get(hotelDeVille).x;
		return positions.get(hotelDeVille2).x;
	}

	@Override
	public int positionHotelVilleY(int n) {
		// TODO Auto-generated method stub
		if(n == 1)
			return positions.get(hotelDeVille).y;
		return positions.get(hotelDeVille2).y;
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int hv) {
		// TODO Auto-generated method stub
		double distance = 0.0;
		if(hv == 1)
			distance=positions.get(hotelDeVille).distance(positions.get(hotelDeVille));
		else{
			distance=positions.get(hotelDeVille2).distance(positions.get(hotelDeVille));
		}
		return distance<=51 && (getVillageois(numVillageois).getRace() == HotelDeVille(hv).appartenance()) 
				|| HotelDeVille(hv).appartenance() == ERace.RIEN;
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
	public int positionRouteX(IRouteService r) {
		// TODO Auto-generated method stub
		return positions.get(r).x;
	}

	@Override
	public int positionRouteY(IRouteService r) {
		// TODO Auto-generated method stub
		return positions.get(r).y;
	}

	@Override
	public boolean estSurRoute(int numVillageois) {
		// TODO Auto-generated method stub
		for(int numRoute = 0; numRoute < routes.size(); numRoute++){
			Rectangle rect = new Rectangle(positionRouteX(getRoute(numRoute)), positionRouteY(getRoute(numRoute)),
					getRoute(numRoute).getLargeur(), getRoute(numRoute).getHauteur());
			if(rect.contains(positions.get(getVillageois(numVillageois))))
				return true;
		}
		return false;
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
	public int positionMurailleX(IMurailleService m) {
		// TODO Auto-generated method stub
		return  positions.get(m).x;
	}

	@Override
	public int positionMurailleY(IMurailleService m) {
		// TODO Auto-generated method stub
		return positions.get(m).y;
	}

	public boolean estSurMuraille(Point p) {
		// TODO Auto-generated method stub
		for(int i = 0; i < murailles.size(); i++){
			Rectangle rect = new Rectangle(positionMurailleX(getMuraille(i)) 
					,positionMurailleY(getMuraille(i))
					,getMuraille(i).getLargeur()
					,getMuraille(i).getHauteur());
			if(rect.contains(p))
				return true;
		}
		return false;
	}
	
	public List<Integer> VillageoisAttente(){
		return VillageoisAttente;
	}
	
	public List<Integer> MineMinee(){
		return MineMinee;
	}
	
	public HashMap<IVillageoisService, Point> positionsVillageois(){
		return positionsVillageois;
	}
	
	public HashMap<Object, Point> positions(){
		return positions;
	}
	@Override
	// Chaque joueur commence avec 2 villageois et 1 hotel de ville. Le terrain contient 4 mines, 2 routes, 
	// 1 muraille 
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		positions =new HashMap<Object, Point>();
		positionsVillageois = new HashMap<IVillageoisService, Point>();

//		positions.put(hotelDeVille, new Point(l/2, 10));
//		positions.put(hotelDeVille2, new Point(l/2, h -60));

		villageois= new ArrayList<IVillageoisService>();
//		IVillageoisService v = new VillageoisImplem();
//		IVillageoisService v1 = new VillageoisImplem();
//		IVillageoisService v2 = new VillageoisImplem();
//		IVillageoisService v3 = new VillageoisImplem();

//		v.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
//		v1.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
//		v2.init(ERace.ORC, 10, 10, 4, 3, 60);
//		v3.init(ERace.ORC, 10, 10, 4, 3, 60);
//
//		positionsVillageois.put(v, new Point(positionHotelVilleX(1) - 10, positionHotelVilleY(1)));
//		positionsVillageois.put(v1, new Point(positionHotelVilleX(1) + 60, positionHotelVilleY(1)));
//		positionsVillageois.put(v2, new Point(positionHotelVilleX(2) - 10, positionHotelVilleY(2)));
//		positionsVillageois.put(v3,  new Point(positionHotelVilleX(2) + 60, positionHotelVilleY(2)));
//		villageois.add(v);
//		villageois.add(v1);
//		villageois.add(v2);
//		villageois.add(v3);

		VillageoisAttente = new ArrayList<>();
//		for(int i = 0; i < villageois.size(); i++){
//			VillageoisAttente.add(-1);
//		}

		MineMinee = new ArrayList<>();
		for(int i = 0; i < villageois.size(); i++){
			MineMinee.add(-1);
		}
		mines=new ArrayList<IMineService>();
//		IMineService m = new MineImplem();
//		IMineService m1 = new MineImplem();
//		IMineService m2 = new MineImplem();
//		IMineService m3 = new MineImplem();
//
//		m.init(50, 50);
//		m1.init(50, 50);
//		m2.init(50, 50);
//		m3.init(50, 50);
//
//		positions.put(m, new Point(10, 10));
//		positions.put(m1, new Point(l-60, 10));
//		positions.put(m2, new Point(l-60, h-60));
//		positions.put(m3, new Point(10, h-60));
//		mines.add(m);
//		mines.add(m1);
//		mines.add(m2);
//		mines.add(m3);

		routes=new ArrayList<IRouteService>();
//		IRouteService r = new RouteImplem();
//		IRouteService r1 = new RouteImplem();
//
//		r.init(l, 50);
//		r1.init(50 , h - 150);
//
//		positions.put(r, new Point(0, h/2));
//		positions.put(r1, new Point(l/2, 80));
//
//		routes.add(r);
//		routes.add(r1);

		murailles=new ArrayList<IMurailleService>();
//		IMurailleService mu = new MurailleImplem();
//
//		mu.init(50, 50, 100);
//
//		positions.put(mu, new Point(l/2, h/2));
//
//		murailles.add(mu);

		this.maxPasJeu=maxPasJeu;
		pasJeuCourant=0;
		largeurTerrain = l;
		hauteurTerrain = h;
		estFini = false;

		return this;

	}



	private Point deplacer(int argument,Point point,int numVillageois){
		int vitesse = villageois.get(numVillageois).getVitesse();

		if(estSurRoute(numVillageois)){
			vitesse = 2*vitesse;
		}
		if(argument<46){
			vitesse = vitesse/2 + 1;
			return new Point(point.x+vitesse,point.y-vitesse);
		}
		if(argument<91){
			return new Point(point.x,point.y-vitesse);
		}
		if(argument<136){
			vitesse = vitesse/2 + 1;
			return new Point(point.x-vitesse,point.y-vitesse);
		}
		if(argument<181){
			return new Point(point.x-vitesse,point.y);
		}
		if(argument<226){
			vitesse = vitesse/2 + 1;
			return new Point(point.x-vitesse,point.y+vitesse);
		}
		if(argument<271){
			return new Point(point.x,point.y+vitesse);
		}
		if(argument<316){
			vitesse = vitesse/2 + 1;
			return new Point(point.x+vitesse,point.y+vitesse);
		}
		if(argument<361){
			return new Point(point.x+vitesse,point.y);
		}

		return point;
	}

	@Override
	public IMoteurJeuService pasJeu(ECommande Commande, ECommande Commande2, int numVillageois, int numVillageois2
			, int argument, int argument2) {
		// TODO Auto-generated method stub

		switch(Commande){
		case DEPLACER : 
			Point yo=deplacer(argument,positionsVillageois.get(villageois.get(numVillageois)), numVillageois);
			// On teste si la position finale yo est sur une muraille;
		    Rectangle r = new Rectangle(0,0, LargeurTerrain(), HauteurTerrain());
			if(!estSurMuraille(yo) && r.contains(new Point(yo.x, yo.y)) && r.contains(new Point(yo.x + 10, yo.y))
					&& r.contains(new Point(yo.x +10, yo.y + 10)) && r.contains(new Point(yo.x, yo.y+10)))
				positionsVillageois.put(villageois.get(numVillageois), yo);
			break;

		case ENTRERMINE : 
			//ajouter un boolean au villageois actif/inactif

			//tester si la mine si abandonnee -> non abandonnee
			if(getMine(argument).estAbandonnee()){
				getMine(argument).accueil(getVillageois(numVillageois).getRace());
			}
			VillageoisAttente.add(numVillageois, pasJeuCourant() + 16);
			MineMinee().add(numVillageois, argument);
			break;


		case ENTRERHOTELVILLE : 
			// qtor(hotelVille) = qtor(hotelVille) + qtor(villageois)
			if(HotelDeVille(argument).estAbandonnee()){
				HotelDeVille(argument).accueil(getVillageois(numVillageois).getRace());
			}
			HotelDeVille(argument).setOrRestant(HotelDeVille(argument).orRestant() 
					+ villageois.get(numVillageois).getQtor());

			//villageois a plus de piece :)
			//qtor(villageois)=0
			villageois.get(numVillageois).setQtor(0);
			break;

		default:
			break;


		}

		switch(Commande2){
		case DEPLACER : 
			Point yo=deplacer(argument2,positionsVillageois.get(villageois.get(numVillageois2)), numVillageois2);
			// On teste si la position finale yo est sur une muraille;
		    Rectangle r = new Rectangle(0,0, LargeurTerrain(), HauteurTerrain());
			if(!estSurMuraille(yo) && r.contains(new Point(yo.x, yo.y)) && r.contains(new Point(yo.x + 10, yo.y))
					&& r.contains(new Point(yo.x +10, yo.y + 10)) && r.contains(new Point(yo.x, yo.y+10)))
				positionsVillageois.put(villageois.get(numVillageois), yo);
			break;

		case ENTRERMINE : 
			//ajouter un boolean au villageois actif/inactif

			//tester si la mine si abandonnee -> non abandonnee
			if(getMine(argument2).estAbandonnee()){
				getMine(argument2).accueil(getVillageois(numVillageois2).getRace());
			}
			VillageoisAttente.add(numVillageois2, pasJeuCourant() + 16);
			MineMinee().add(numVillageois2, argument2);
			break;

		case ENTRERHOTELVILLE : 
			// qtor(hotelVille) = qtor(hotelVille) + qtor(villageois)
			if(HotelDeVille(argument2).estAbandonnee()){
				HotelDeVille(argument2).accueil(getVillageois(numVillageois2).getRace());
			}
			HotelDeVille(argument2).setOrRestant(HotelDeVille(argument2).orRestant() 
					+ villageois.get(numVillageois2).getQtor());

			//villageois a plus de piece :)
			//qtor(villageois)=0
			villageois.get(numVillageois2).setQtor(0);
			break;


		}

		for(int i = 0; i < VillageoisAttente.size(); i++){
			if(VillageoisAttente.get(i) == pasJeuCourant){
				VillageoisAttente.add(numVillageois, -1);
				villageois.get(i).setQtor(villageois.get(i).getQtor() + 1);
				getMine(MineMinee().get(i)).setOrRestant(getMine(MineMinee().get(i)).orRestant() - 1); 
				MineMinee().add(i, -1);
			}
		}

		// On modifie les abandons compteurs de la mine et on regarde la quantite d'or de la mine
		for(int i = 0; i < numerosMine().size(); i++){
			if(getMine(i).estLaminee())
				continue;
			boolean villageoisDansMine = false;
			int nbVillageoisDansMine = 0;
			for(int j = 0; j < MineMinee.size(); j++){
				if(MineMinee().get(i) == i){
					villageoisDansMine = true;
					nbVillageoisDansMine++;
				}			
			}
			if(getMine(i).abandonCompteur() < 51 && !villageoisDansMine){
				getMine(i).setAbandonCompteur(getMine(i).abandonCompteur() + 1);
			}
			else if(getMine(i).abandonCompteur() == 51 && !getMine(i).estAbandonnee()){
				getMine(i).abandoned();
			}
			
			if(getMine(i).orRestant() == 0 || getMine(i).orRestant() - nbVillageoisDansMine <= 0){
				getMine(i).setLaminee();
			}
		}

		// On modifie les abandons compteurs des hotels de ville
		if(HotelDeVille(1).abandonCompteur() < 51 && Commande != ECommande.ENTRERHOTELVILLE ||(Commande == ECommande.ENTRERHOTELVILLE && argument != 1)){
			HotelDeVille(1).setAbandonCompteur(HotelDeVille(1).abandonCompteur() + 1);
		}
		else if(HotelDeVille(1).abandonCompteur() == 51){
			HotelDeVille(1).abandoned();
		}
		if(HotelDeVille(2).abandonCompteur() < 51 && Commande2 != ECommande.ENTRERHOTELVILLE ||(Commande2 == ECommande.ENTRERHOTELVILLE && argument2 != 2)){
			HotelDeVille(2).setAbandonCompteur(HotelDeVille(2).abandonCompteur() + 1);
		}
		else if(HotelDeVille(2).abandonCompteur() == 51 && !HotelDeVille(2).estAbandonnee()){
			HotelDeVille(2).abandoned();
		}
		
		pasJeuCourant++;
		
		return this;
	}

	@Override
	public void bindHotelVille(Point positionHotelVille1,
			Point positionHotelVille2) {
		hotelDeVille = new HotelVilleImplem();
		hotelDeVille2 = new HotelVilleImplem();

		hotelDeVille.init(50, 50, ERace.HUMAIN);
		hotelDeVille2.init(50, 50, ERace.ORC);
		positions.put(HotelDeVille(1), positionHotelVille1);
		positions.put(HotelDeVille(2), positionHotelVille2);
	}

	@Override
	public void bindMine(List<IMineService> mines,
			List<Point> positionsMines) {
		this.mines = mines;
		for(int i = 0; i < mines.size(); i++){
			positions().put(mines.get(i), positionsMines.get(i));
		}		
	}

	@Override
	public void bindRoute(List<IRouteService> routes,
			List<Point> positionsRoutes) {
		this.routes = routes;
		for(int i = 0; i < routes.size(); i++){
			positions().put(routes.get(i), positionsRoutes.get(i));
		}			
	}

	@Override
	public void bindVillageois(List<IVillageoisService> villageois,
			List<Point> positionsVillageois) {
		this.villageois = villageois;
		for(int i = 0; i < villageois.size(); i++){
			positionsVillageois().put(villageois.get(i), positionsVillageois.get(i));
			if(MineMinee())
			MineMinee().add(-1);
			VillageoisAttente.add(-1);
		}
		
	}

	@Override
	public void bindMuraille(List<IMurailleService> murailles,
			List<Point> positionsMurailles) {
		this.murailles = murailles;
		for(int i = 0; i < murailles.size(); i++){
			positions().put(murailles.get(i), positionsMurailles.get(i));
		}	
	}
	
	public void showMap() {
		
	}
	

}
