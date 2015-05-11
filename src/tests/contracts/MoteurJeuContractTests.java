package tests.contracts;



import java.awt.Point;
import java.util.ArrayList;

import implementations.HotelVilleImplem;
import implementations.MineImplem;
import implementations.MoteurJeuImplem;
import implementations.MurailleImplem;
import implementations.RouteImplem;
import implementations.VillageoisImplem;

import org.junit.Before;
import org.junit.Test;
import contracts.MoteurJeuContract;
import enums.ERace;
import exceptions.PreconditionError;
import services.IHotelVilleService;
import services.IMineService;
import services.IMurailleService;
import services.IRouteService;
import services.IVillageoisService;
import tests.AbstractMoteurJeu;

public class MoteurJeuContractTests extends AbstractMoteurJeu{
	@Override
	
	@Before
	public void before() {
		moteurJeu = new MoteurJeuImplem();	
		
		IHotelVilleService hotelDeVille = new HotelVilleImplem();
		IHotelVilleService hotelDeVille2 = new HotelVilleImplem();

		hotelDeVille.init(50, 50, ERace.HUMAIN);
		hotelDeVille2.init(50, 50, ERace.ORC);

		ArrayList<IVillageoisService> villageois= new ArrayList<IVillageoisService>();
		ArrayList<Point> positionsVillageois = new ArrayList<Point>();
		IVillageoisService v = new VillageoisImplem();
		IVillageoisService v1 = new VillageoisImplem();
		IVillageoisService v2 = new VillageoisImplem();
		IVillageoisService v3 = new VillageoisImplem();

		v.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v1.init(ERace.HUMAIN, 10, 10, 3, 4, 60);
		v2.init(ERace.ORC, 10, 10, 4, 3, 60);
		v3.init(ERace.ORC, 10, 10, 4, 3, 60);
		//
		//moteurJeu.positions
		positionsVillageois.add( new Point(500-30,60));
		positionsVillageois.add( new Point(500+30,60));
		positionsVillageois.add( new Point(500-30, 940));
		positionsVillageois.add(new Point(500, 940 - 30));

		villageois.add(v);
		villageois.add(v1);
		villageois.add(v2);
		villageois.add(v3);


		ArrayList<IMineService> mines=new ArrayList<IMineService>();
		ArrayList<Point> positionsMines = new ArrayList<Point>();
		IMineService m = new MineImplem();
		IMineService m1 = new MineImplem();
		IMineService m2 = new MineImplem();
		IMineService m3 = new MineImplem();
		m.init(50, 50);
		m1.init(50, 50);
		m2.init(50, 50);
		m3.init(50, 50);
		//
		positionsMines.add( new Point(10, 10));
		positionsMines.add( new Point(1000-60, 10));
		positionsMines.add( new Point(1000-60, 1000-60));
		positionsMines.add( new Point(10,1000-60));

		mines.add(m);
		mines.add(m1);
		mines.add(m2);
		mines.add(m3);


		ArrayList<IRouteService> routes=new ArrayList<IRouteService>();
		ArrayList<Point> positionsRoutes = new ArrayList<Point>();
		IRouteService r = new RouteImplem();
		IRouteService r1 = new RouteImplem();

		r.init(1000, 50);
		r1.init(50 , 1000 - 150);
		//
		positionsRoutes.add( new Point(0, 1000/2));
		positionsRoutes.add( new Point(1000/2, 80));
		//
		routes.add(r);
		routes.add(r1);

		ArrayList<IMurailleService> murailles=new ArrayList<IMurailleService>();
		IMurailleService mu = new MurailleImplem();
		//
		mu.init(50, 50, 100);
		//
		ArrayList<Point> positionsMurailles = new ArrayList<Point>();
		positionsMurailles.add( new Point(1000/2, 1000/2));
		//
		murailles.add(mu);

		moteurJeu.init(1664,1000,1000);

		moteurJeu.bindMine(mines, positionsMines);
		moteurJeu.bindMuraille(murailles, positionsMurailles);
		moteurJeu.bindRoute(routes, positionsRoutes);
		moteurJeu.bindVillageois(villageois, positionsVillageois);
		System.out.println(positionsVillageois);
		moteurJeu.bindHotelVille(hotelDeVille,hotelDeVille2,new Point(500,60), new Point(500,940));
	}
	
	@Test(expected=PreconditionError.class)
	public void test0_2() {
		// condition initale
		
		// opérations
		//moteurJeu.init(-1, -1, -1);
		
		// oracle (Cf. expected dans le @Test)
	}
}
