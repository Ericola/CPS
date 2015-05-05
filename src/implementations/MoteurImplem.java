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
	private IHotelVilleService hotelDeVille2;
	private List<IRouteService> routes;
	private List<IMurailleService> murailles;

	private List<Integer> villageoisAttente;
	
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
	public IHotelVilleService HotelDeVille2() {
		// TODO Auto-generated method stub
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

	public boolean estSurMuraille(Point p) {
		// TODO Auto-generated method stub
		for(int i = 0; i < murailles.size(); i++){
			Rectangle rect = new Rectangle(positionMurailleX(i) 
					,positionMurailleY(i)
					,routes.get(i).getLargeur()
					,routes.get(i).getHauteur());
			if(rect.contains(p))
				return false;
		}
		return true;
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
	// Chaque joueur commence avec 2 villageois et 1 hotel de ville. Le terrain contient 4 mines, 2 routes, 
	// 1 muraille 
	public IMoteurJeuService init(int maxPasJeu, int l, int h) {
		// TODO Auto-generated method stub
		positions =new HashMap<Object, Point>();

		hotelDeVille = new HotelVilleImplem();
		hotelDeVille2 = new HotelVilleImplem();

		hotelDeVille.init(50, 50);
		hotelDeVille2.init(50, 50);

		positions.put(hotelDeVille, new Point(l/2, 10));
		positions.put(hotelDeVille2, new Point(l/2, h -60));

		villageois= new ArrayList<IVillageoisService>();
		IVillageoisService v = new VillageoisImplem();
		IVillageoisService v1 = new VillageoisImplem();
		IVillageoisService v2 = new VillageoisImplem();
		IVillageoisService v3 = new VillageoisImplem();

		v.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v1.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v2.init(ERace.ORC, 10, 10, 4, 3, 60);
		v3.init(ERace.ORC, 10, 10, 4, 3, 60);

		positions.put(v, new Point(positionHotelVilleX(1) - 10, positionHotelVilleY(1)));
		positions.put(v1, new Point(positionHotelVilleX(1) + 60, positionHotelVilleY(1)));
		positions.put(v2, new Point(positionHotelVilleX(2) - 10, positionHotelVilleY(2)));
		positions.put(v3,  new Point(positionHotelVilleX(2) + 60, positionHotelVilleY(2)));
		villageois.add(v);
		villageois.add(v1);
		villageois.add(v2);
		villageois.add(v3);

		villageoisAttente = new ArrayList<>();
		for(int i = 0; i < villageois.size(); i++){
			villageoisAttente.add(0);
		}
		
		mines=new ArrayList<IMineService>();
		IMineService m = new MineImplem();
		IMineService m1 = new MineImplem();
		IMineService m2 = new MineImplem();
		IMineService m3 = new MineImplem();

		m.init(50, 50);
		m1.init(50, 50);
		m2.init(50, 50);
		m3.init(50, 50);

		positions.put(m, new Point(10, 10));
		positions.put(m1, new Point(l-60, 10));
		positions.put(m2, new Point(l-60, h-60));
		positions.put(m3, new Point(10, h-60));
		mines.add(m);
		mines.add(m1);
		mines.add(m2);
		mines.add(m3);

		routes=new ArrayList<IRouteService>();
		IRouteService r = new RouteImplem();
		IRouteService r1 = new RouteImplem();

		r.init(l, 50);
		r1.init(50 , h - 150);

		positions.put(r, new Point(0, h/2));
		positions.put(r1, new Point(l/2, 80));

		routes.add(r);
		routes.add(r1);

		murailles=new ArrayList<IMurailleService>();
		IMurailleService mu = new MurailleImplem();

		mu.init(50, 50, 100);

		positions.put(mu, new Point(l/2, h/2));

		murailles.add(mu);

		this.maxPasJeu=maxPasJeu;
		pasJeuCourant=0;
		largeurTerrain = l;
		hauteurTerrain = h;
		estFini = false;

		return this;

	}



	public Point deplacer(int argument,Point point,int numVillageois){
		int vitesse = villageois.get(numVillageois).getVitesse();
		 Label ("Key Listener!" ) ;
			p.add(l1);  
			add(p);
			addKeyListener ( this ) ; 
			setSize ( 200,100 );
			setVisible(true);
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
			this.moteurJeu = moteurJeu;
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
			Point yo=deplacer(argument,positions.get(villageois.get(numVillageois)), numVillageois);
			// On teste si la position finale yo est sur une muraille;
			if(!estSurMuraille(yo))
				positions.put(villageois.get(numVillageois), yo);
			break;

		case ENTRERMINE : break;
		//ajouter un boolean au villageois actif/inactif

		//tester si la mine si abandonnée -> non abandonnée
		// tester si la mine appartient a ta race. Si non On peut pas entrer !!!!!
		//mettre le bool a inactif
		//une fois les 16pas de jeu fini le remettre actif
		//il a une piece :)
		//qtor++
		//villageoisAttente.add(numVillageois, pasJeuCourant + 16)
		
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

		switch(Commande2){
		case DEPLACER : 
			Point yo=deplacer(argument2,positions.get(villageois.get(numVillageois2)), numVillageois2);
			positions.put(villageois.get(numVillageois2), yo);
			break;

		case ENTRERMINE : break;
		//ajouter un boolean au villageois actif/inactif

		//tester si la mine si abandonné -> non abandonné

		//mettre le bool a inactif
		//une fois les 16pas de jeu fini le remettre actif
		//il a une piece :)
		//qtor++
		//villageoisAttente.add(numVillageois, pasJeuCourant + 16)

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
		
		for(int i = 0; i < villageoisAttente.size(); i++){
			if(villageoisAttente.get(i) == pasJeuCourant){
				villageoisAttente.add(numVillageois, 0);
			}
		}
		
		pasJeuCourant++;
		
		
		return this;
	}

}
